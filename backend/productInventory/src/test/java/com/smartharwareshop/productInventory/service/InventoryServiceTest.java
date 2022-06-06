package com.smartharwareshop.productInventory.service;
import com.smartharwareshop.productInventory.exception.InvalidProductDescriptionException;
import com.smartharwareshop.productInventory.exception.InvalidQuantityException;
import com.smartharwareshop.productInventory.exception.InvalidSearchRegex;
import com.smartharwareshop.productInventory.exception.ProductNotFoundInCartException;
import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.model.ProductId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InventoryServiceTest {

  private InventoryService inventoryService;
  private List<Product> products;

  @BeforeAll
  public void setup() {
    inventoryService = new InventoryService();
    InMemoryProductInventory inventory = InMemoryProductInventory.getInstance();
    inventory.clearInventory();
    ProductDescription productDescription = new ProductDescription("test", 1, 10.5, 5, false);
    inventoryService.addProduct(productDescription);
    products = inventoryService.getProducts();
  }

  @Test
  public void getALLProductsTest() { assertEquals(1, products.size()); }

  @Test
  public void getProductsForIdsTest() {
    List<ProductId> productIds = new ArrayList<>();
    productIds.add(products.get(0).productId());
    List<Product> productFromIds = inventoryService.getProducts(productIds);
    assertEquals(products.get(0).description().name(), productFromIds.get(0).description().name());
  }

  @Test
  public void updateInventoryTest() {
    Product product = inventoryService.getProducts().get(0);
    ProductDescription description = new ProductDescription("test", 1, 100.0, 10, true);
    ProductId id = product.productId();
    Product updatedProduct = new Product(id, description);
    inventoryService.updateInventory(updatedProduct);
    Product testProduct = inventoryService.getProducts().stream().filter(pd -> pd.productId().id().equals(id.id())).toList().get(0);
    assertEquals(100.0, testProduct.description().price());
  }

  @Test
  public void addProduct() {
    ProductDescription description = new ProductDescription("test", 1, 100.0, 10, true);
    inventoryService.addProduct(description);
    List<Product> products = inventoryService.getProducts();
    assertEquals(2, products.size());
  }

  @Test
  public void removeProduct() {
    ProductDescription description = new ProductDescription("test", 1, 100.0, 10, true);
    inventoryService.addProduct(description);
    ProductId id = inventoryService.getProducts().get(1).productId();
    inventoryService.removeProduct(id);
    List<Product> productsAfterRemoval = inventoryService.getProducts();
    assertEquals(1, productsAfterRemoval.size());
  }

  @Test
  public void productNotFoundExceptionTest() {
    ProductId id = new ProductId(UUID.randomUUID());
    assertThrows(ProductNotFoundInCartException.class, () -> inventoryService.removeProduct(id));
  }

  @Test
  public void invalidProductDescriptionExceptionTest() {
    ProductDescription productDescription = new ProductDescription(" ", 1, 0, 6, false);
    assertThrows(InvalidProductDescriptionException.class, () -> inventoryService.addProduct(productDescription));
  }

  @Test
  public void invalidQuantityExceptionTest() {
    ProductDescription productDescription = new ProductDescription("name", 1, 10.2, -1, false);
    assertThrows(InvalidQuantityException.class, () -> inventoryService.addProduct(productDescription));
  }

  @Test
  public void invalidSearchRegexExceptionTest() {
    String test = null;
    assertThrows(InvalidSearchRegex.class, () -> inventoryService.searchProducts(test));
  }

}
