package com.smartharwareshop.productInventory.service;
import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemoryProductInventoryTest {

  private InMemoryProductInventory inventory;
  private List<Product> products;

  @BeforeAll
  public void setup() {
    inventory = InMemoryProductInventory.getInstance();
    inventory.clearInventory();
    ProductDescription productDescription = new ProductDescription("test", 1, 10.5, 5, false);
    inventory.addProductIntoInventory(productDescription);
    products = inventory.getProductsFromInventory();
  }

  @Test
  public void testSingletonObjectOfStoretest() {
    InMemoryProductInventory store1 = InMemoryProductInventory.getInstance();
    InMemoryProductInventory store2 = InMemoryProductInventory.getInstance();
    assertEquals(store1, store2);
  }

  @Test
  public void getProductsFromInventorytest() {
    assertEquals(1, products.size());
  }

  @Test
  public void updateProductIntoInventorytest() {
    Product product = inventory.getProductsFromInventory().get(0);
    ProductDescription description = new ProductDescription("test", 1, 10.5, 10, true);
    Product updatedProduct = new Product(product.productId(), description);
    inventory.updateProductIntoInventory(updatedProduct);
    List<Product> products = inventory.getProductsFromInventory();
    assertEquals(10, products.get(0).description().availableCount());
  }

  @Test
  public void addProductIntoInventorytest() {
    ProductDescription description = new ProductDescription("test 2", 2, 100, 10, false);
    inventory.addProductIntoInventory(description);
    List<Product> products = inventory.getProductsFromInventory();
    assertEquals(2, products.size());
  }

  @Test
  public void removeProductFromInventorytest() {
    ProductDescription description = new ProductDescription("test 3", 2, 100, 10, false);
    inventory.addProductIntoInventory(description);
    Product product  = inventory.getProductsFromInventory().get(0);
    inventory.removeProductFromInventory(product.productId());
    assertEquals(1, inventory.getProductsFromInventory().size());
  }
}
