package com.smartharwareshop.productInventory.controller;

import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.model.ProductId;
import com.smartharwareshop.productInventory.model.SearchText;
import com.smartharwareshop.productInventory.service.Inventory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class InventoryController {

  private final Inventory inventory;

  public InventoryController(Inventory inventory) {
    this.inventory = inventory;
  }

  @GetMapping(value = "/products")
  public List<Product> getProducts() {
    return inventory.getProducts();
  }

  @PostMapping(value = "/products")
  public List<Product> getProducts(@RequestBody List<ProductId> productIds) { return inventory.getProducts(productIds); }

  @PostMapping(value = "/search")
  public List<Product> searchProducts(@RequestBody SearchText searchText) { return inventory.searchProducts(searchText.text()); }

  @GetMapping(value="/productHighlight")
  public List<Product> getHighlightedProducts() { return inventory.highlightedProduct(); }

  @PostMapping(value = "/product")
  public void addProduct(@RequestBody ProductDescription productDescription) { inventory.addProduct(productDescription); }

  @PutMapping(value = "/product")
  public void updateProductQuantity(@RequestBody Product product) { inventory.updateInventory(product); }

  @DeleteMapping(value = "/product/{productId}")
  public void removeProductFromInventory(@PathVariable ProductId productId) { inventory.removeProduct(productId); }
}
