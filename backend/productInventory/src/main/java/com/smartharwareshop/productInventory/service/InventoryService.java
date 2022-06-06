package com.smartharwareshop.productInventory.service;

import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.model.ProductId;
import com.smartharwareshop.productInventory.util.InventoryOperationValidator;
import com.smartharwareshop.productInventory.util.ProductSearch;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService implements Inventory {

  private final InMemoryProductInventory inMemoryProductInventory;

  public InventoryService() {
    this.inMemoryProductInventory = InMemoryProductInventory.getInstance();
  }

  public List<Product> getProducts() { return inMemoryProductInventory.getProductsFromInventory(); }

  public List<Product> getProducts(List<ProductId> productIds) { return inMemoryProductInventory.getProductsFromInventory(productIds); }

  public void updateInventory(Product product) {
    inMemoryProductInventory.updateProductIntoInventory(product);
  }

  public void addProduct(ProductDescription productDescription) { inMemoryProductInventory.addProductIntoInventory(productDescription); }

  public void removeProduct(ProductId productId) {
    inMemoryProductInventory.removeProductFromInventory(productId);
  }

  public List<Product> searchProducts(String text) {
    if (InventoryOperationValidator.isSearchRegexValid(text))
      return ProductSearch.filterProductsBasedOnRegex(getProducts(), text);
    else if (text.isBlank()) {
      return getProducts();
    }
    return new ArrayList<Product>();
  }

  public List<Product> highlightedProduct() { return ProductSearch.getHighlightedProduct(getProducts()); }

}
