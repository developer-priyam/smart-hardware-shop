package com.smartharwareshop.shoppingcart.service;

import com.smartharwareshop.shoppingcart.model.CartItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemoryCartStorageTest {

  private UUID userId;
  private UUID productId1;
  private UUID productId2;
  private UUID productId3;

  @BeforeAll
  public void setup() {
    userId = UUID.randomUUID();
    productId1 = UUID.randomUUID();
    productId2 = UUID.randomUUID();
    productId3 = UUID.randomUUID();
  }


  @Test
  public void testSingletonObjectOfStore() {
    InMemoryCartStorage store1 = InMemoryCartStorage.getInstance();
    InMemoryCartStorage store2 = InMemoryCartStorage.getInstance();

    assertEquals(store1, store2);
  }

  @Test
  public void testCartOperation() {
    InMemoryCartStorage store = InMemoryCartStorage.getInstance();

    store.addProductIntoCart(userId, productId1);
    store.addProductIntoCart(userId, productId2);
    store.addProductIntoCart(userId, productId3);

    List<CartItem> items = store.getShoppingCartOfTheUser(userId);

    assertEquals(3, items.size());

    store.deleteProductFromCart(userId, productId1);

    items = store.getShoppingCartOfTheUser(userId);

    assertEquals(2, items.size());

    store.updateShoppingCart(userId, productId2, 4);

    items = store.getShoppingCartOfTheUser(userId);

    assertEquals(2, items.size());

    store.updateShoppingCart(userId, productId2, 6);
    store.updateShoppingCart(userId, productId3, 3);

    items = store.getShoppingCartOfTheUser(userId);

    assertEquals(2, items.size());

  }
}
