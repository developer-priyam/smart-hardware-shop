package com.smartharwareshop.productInventory.service;

import com.smartharwareshop.productInventory.exception.InvalidQuantityException;
import com.smartharwareshop.productInventory.exception.ProductNotFoundInCartException;
import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.model.ProductId;
import com.smartharwareshop.productInventory.util.InventoryOperationValidator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemoryProductInventory {

  private static InMemoryProductInventory inMemoryProductInventory;
  private static final Map<ProductId, Product> inventory = new HashMap<>();
  private InMemoryProductInventory() {}

  public static InMemoryProductInventory getInstance() {
    if (inMemoryProductInventory == null) {
      synchronized (InMemoryProductInventory.class) {
        if (inMemoryProductInventory == null) {
          inMemoryProductInventory = new InMemoryProductInventory();
        }
      }
    }
    return inMemoryProductInventory;
  }

  public List<Product> getProductsFromInventory() { return inventory.values().stream().toList(); }

  public List<Product> getProductsFromInventory(List<ProductId> productIds) {
    List<Product> filteredProducts = new ArrayList<>();
    List<Product> productList = getProductsFromInventory();
    for(ProductId productId : productIds) {
      List<Product> product = productList.stream().filter(productObj -> productObj.productId().id().equals(productId.id())).toList();;
      if (!product.isEmpty()) {
        filteredProducts.add(product.get(0));
      }
    }
    return filteredProducts;
  }

  public void updateProductIntoInventory(Product product) {
    Product updatedProduct;
    if (inventory.containsKey(product.productId())) {
      updatedProduct = new Product(product.productId(),
        product.description());
      if (product.description().availableCount() > 0) inventory.put(product.productId(), updatedProduct);
    } else {
      throw new ProductNotFoundInCartException("product not found in the Inventory");
    }
  }

  public void addProductIntoInventory(ProductDescription productDescription) {
    ProductId productId = new ProductId(UUID.randomUUID());
    if(InventoryOperationValidator.validateProductDescription(productDescription) && productDescription.availableCount() < 0)
      throw new InvalidQuantityException("Invalid!! Please provide a valid count  of the product for adding into inventory");
    else inventory.put(productId, new Product(productId, productDescription));
  }

  public void removeProductFromInventory(ProductId productId) {
    if(inventory.containsKey(productId)) {
      inventory.remove(productId);
    } else {
      throw new ProductNotFoundInCartException("product not found in the Inventory");
    }
  }

  void clearInventory() {  //just used in test cases
    inventory.clear();;
  }

}
