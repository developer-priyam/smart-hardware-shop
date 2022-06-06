package com.smartharwareshop.productInventory.util;

import com.smartharwareshop.productInventory.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductSearch {

  private ProductSearch() {}

  public static List<Product> filterProductsBasedOnRegex(List<Product> products, String text) {
    return products.stream().filter(product -> product.description().name().toLowerCase().contains(text)).limit(10).collect(Collectors.toList());
  }

  public static List<Product> getHighlightedProduct(List<Product> products) {
    return products.stream().filter(product -> product.description().isHighlighted()).collect(Collectors.toList());
  }
}
