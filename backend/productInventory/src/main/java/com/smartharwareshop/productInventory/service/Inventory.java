package com.smartharwareshop.productInventory.service;

import com.smartharwareshop.productInventory.model.Product;
import com.smartharwareshop.productInventory.model.ProductDescription;
import com.smartharwareshop.productInventory.model.ProductId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Inventory {

  List<Product> getProducts();

  List<Product> getProducts(List<ProductId> productIds);

  void updateInventory(Product product);

  void addProduct(ProductDescription productDescription);

  void removeProduct(ProductId productId);

  List<Product> searchProducts(String text);

  List<Product> highlightedProduct();
}
