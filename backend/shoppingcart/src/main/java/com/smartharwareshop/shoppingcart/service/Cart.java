package com.smartharwareshop.shoppingcart.service;

import com.smartharwareshop.shoppingcart.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface Cart {

  List<CartItem> getProductsInCart(UUID userId);

  void addProductIntoCart(UUID userId, UUID productId);

  void updateShoppingCartForTheUser(UUID userId, UUID productId, int quantity) ;

  void deleteProductFromCart(UUID userId, UUID productId);

  void clearCart(UUID userId);

}
