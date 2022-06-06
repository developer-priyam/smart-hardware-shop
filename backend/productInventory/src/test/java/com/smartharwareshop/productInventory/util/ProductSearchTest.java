package com.smartharwareshop.productInventory.util;

import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.service.InMemoryProductInventory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductSearchTest {

  private List<Product> products;

  @BeforeAll
  public void setup() {
    InMemoryProductInventory inventory = InMemoryProductInventory.getInstance();
    ProductDescription productDescription = new ProductDescription("drill machine", 1, 100, 5, false);
    ProductDescription productDescription2 = new ProductDescription("screwdriver", 1, 10, 3, true);
    inventory.addProductIntoInventory(productDescription);
    inventory.addProductIntoInventory(productDescription2);
    products = inventory.getProductsFromInventory();
  }

  @Test
  public void testHighlightedSearch() {
    assertEquals(1, ProductSearch.getHighlightedProduct(products).size());
  }

  @Test
  public void testRegexSearch() {
    assertEquals(1, ProductSearch.filterProductsBasedOnRegex(products, "drill").size());
  }

}
