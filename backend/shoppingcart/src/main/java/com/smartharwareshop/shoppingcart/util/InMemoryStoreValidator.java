package com.smartharwareshop.shoppingcart.util;

import com.smartharwareshop.shoppingcart.exception.InvalidProductException;
import com.smartharwareshop.shoppingcart.exception.InvalidQuantityException;
import com.smartharwareshop.shoppingcart.exception.InvalidUserException;
import com.smartharwareshop.shoppingcart.exception.ProductNotFoundInCartException;
import com.smartharwareshop.shoppingcart.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class InMemoryStoreValidator {

  private InMemoryStoreValidator() {}

  public static boolean isValidUser(UUID userId, Set<UUID> userIds) {
    if (userIds.contains(userId)) {
      return true;
    } else {
      throw new InvalidUserException("Request user not found in Database. Please register this user.");
    }
  }

  public static boolean isValidQuantity(int quantity) {
    if (quantity >= 0) return true;
    else throw new InvalidQuantityException("Quantity provided is invalid. Should be positive");
  }

  public static boolean isProductIdValid(UUID productId) {
     // TO DO: validate it with inventory database. Not part of this scope of application / service.
    throw new InvalidProductException("Product is invalid. No Such product exists in the DB");
  }

  public static boolean productNotFoundException(UUID productId, List<CartItem> items) {
    Optional<CartItem> foundItem = items.stream().filter(item -> item.productId().equals(productId)).findFirst();
    if (foundItem.isPresent()) return true;
    else throw new ProductNotFoundInCartException("User do no have this product in the cart anymore.");
  }
}
