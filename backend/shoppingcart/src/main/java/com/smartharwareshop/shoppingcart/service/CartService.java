package com.smartharwareshop.shoppingcart.service;

import com.smartharwareshop.shoppingcart.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService implements Cart {

  private final InMemoryCartStorage inMemoryCartStorage;

  public CartService() {
    this.inMemoryCartStorage = InMemoryCartStorage.getInstance();
  }

  public List<CartItem> getProductsInCart(UUID userId) {
    return inMemoryCartStorage.getShoppingCartOfTheUser(userId);
  }

  public void addProductIntoCart(UUID userId, UUID productId) { inMemoryCartStorage.addProductIntoCart(userId, productId); }

  public void updateShoppingCartForTheUser(UUID userId, UUID productId, int quantity) {
    inMemoryCartStorage.updateShoppingCart(userId, productId, quantity);
  }

  public void deleteProductFromCart(UUID userId, UUID productId) {
    inMemoryCartStorage.deleteProductFromCart(userId, productId);
  }

  public void clearCart(UUID userId) { inMemoryCartStorage.clearCart(userId); }
}
