package com.smartharwareshop.shoppingcart.service;

import com.smartharwareshop.shoppingcart.exception.InvalidUserException;
import com.smartharwareshop.shoppingcart.model.CartItem;
import com.smartharwareshop.shoppingcart.util.InMemoryStoreValidator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InMemoryCartStorage {

  private static InMemoryCartStorage inMemoryCartStorage;
  private static final Map<UUID, List<CartItem>> shoppingCart = new HashMap<>();
  private InMemoryCartStorage() {}

  public static InMemoryCartStorage getInstance() {
    if (inMemoryCartStorage == null) {
      synchronized (InMemoryCartStorage.class) {
        if (inMemoryCartStorage == null) {
          inMemoryCartStorage = new InMemoryCartStorage();
        }
      }
    }
    return inMemoryCartStorage;
  }

  /**
   * We will verify users in future, when we have proper authentication and authorization module.
   * @param userId
   * @return
   */
  //    if (InMemoryStoreValidator.isValidUser(userId, shoppingCart.keySet())) {
  public List<CartItem> getShoppingCartOfTheUser(UUID userId) {
      return shoppingCart.get(userId);
  }

  public void addProductIntoCart(UUID userId, UUID productId) {
    List<CartItem> items = new ArrayList<>();
    if (shoppingCart.get(userId) == null) {
      items.add(new CartItem(productId, 1));
     } else {
      items = shoppingCart.get(userId);
      items.add(new CartItem(productId, 1));
    }
    shoppingCart.put(userId, items);
  }

  // Future scope, when we have users in Production System Database then before every update
  // we will check the user id against cached users or users in DB
  // InMemoryStoreValidator.isValidUser(userId, shoppingCart.keySet())
  public void updateShoppingCart(UUID userId, UUID productId, int quantity) {
    if (InMemoryStoreValidator.isValidQuantity(quantity)) {
        List<CartItem> userProductList = new ArrayList<>();
      if (shoppingCart.get(userId) != null) {
        userProductList = shoppingCart.get(userId);
        userProductList = userProductList.stream().filter(item -> !item.productId().equals(productId)).collect(Collectors.toList());
        userProductList.add(new CartItem(productId, quantity));
      }
      shoppingCart.put(userId, userProductList);
    }
  }

  public void deleteProductFromCart(UUID userId, UUID productId) {
    if (InMemoryStoreValidator.isValidUser(userId, shoppingCart.keySet())) {;
      List<CartItem> items = shoppingCart.get(userId);
      List<CartItem> filteredItems = items.stream().filter(item -> !item.productId().equals(productId)).collect(Collectors.toList());
      shoppingCart.put(userId, filteredItems);
    }
  }

  public void clearCart(UUID userId) {
    shoppingCart.put(userId, new ArrayList<CartItem>());
  }
}
