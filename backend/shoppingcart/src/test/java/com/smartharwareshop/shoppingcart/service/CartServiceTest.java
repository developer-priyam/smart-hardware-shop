package com.smartharwareshop.shoppingcart.service;

import com.smartharwareshop.shoppingcart.exception.InvalidProductException;
import com.smartharwareshop.shoppingcart.exception.InvalidQuantityException;
import com.smartharwareshop.shoppingcart.model.CartItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartServiceTest {

  private UUID userId;
  private UUID productId2;

  private CartService cartService;

  @BeforeAll
  public void setup() {
    cartService = new CartService();
    userId = UUID.randomUUID();
    UUID productId1 = UUID.randomUUID();
    productId2 = UUID.randomUUID();
    UUID productId3 = UUID.randomUUID();
    InMemoryCartStorage store = InMemoryCartStorage.getInstance();
    store.addProductIntoCart(userId, productId1);
    store.addProductIntoCart(userId, productId2);
    store.addProductIntoCart(userId, productId3);
  }

  @Test
  public void testCartData() {
    List<CartItem> cartItems = cartService.getProductsInCart(userId);
    assertEquals(3, cartItems.size());
  }

  @Test
  public void testCartUpdate() {
    cartService.updateShoppingCartForTheUser(userId, productId2, 4);
    List<CartItem> items = cartService.getProductsInCart(userId);
    CartItem item = items.stream().filter(ci -> ci.productId().equals(productId2)).findFirst().get();
    assertEquals(4, item.quantity());
  }

  @Test
  public void invaliDQuantityExceptionOnUpdateTest() {
    UUID pid = UUID.randomUUID();
    assertThrows(InvalidQuantityException.class, () -> cartService.updateShoppingCartForTheUser(userId, pid, -1));
  }

}
