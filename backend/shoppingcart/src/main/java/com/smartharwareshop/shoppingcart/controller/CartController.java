package com.smartharwareshop.shoppingcart.controller;

import com.smartharwareshop.shoppingcart.model.CartItem;
import com.smartharwareshop.shoppingcart.service.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart-service")
public class CartController {

  private final Cart cart;

  public CartController(Cart cart) {
    this.cart = cart;
  }

  @GetMapping(value = "/cart/{userId}")
  public List<CartItem> getShoppingCart(@PathVariable UUID userId) {
    return cart.getProductsInCart(userId);
  }

  @PostMapping(value = "/cart/{userId}")
  public void addProductToTheCart(@PathVariable UUID userId, @RequestBody CartItem item) {
    cart.addProductIntoCart(userId, item.productId());
  }

  @PutMapping(value = "/cart/{userId}")
  public void updateProductQuantity(@PathVariable UUID userId, @RequestBody CartItem item) {
    cart.updateShoppingCartForTheUser(userId, item.productId(), item.quantity());
  }

  @DeleteMapping(value = "/cart/{userId}/{productId}")
  public void removeProductFromUserCart(@PathVariable UUID userId, @PathVariable UUID productId) {
    cart.deleteProductFromCart(userId, productId);
  }

  @PutMapping(value = "/cart/clear/{userId}")
  public void clearCart(@PathVariable UUID userId) {
    cart.clearCart(userId);
  }
}
