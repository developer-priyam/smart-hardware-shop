package com.smartharwareshop.productInventory.util;

import com.smartharwareshop.productInventory.exception.InvalidProductDescriptionException;
import com.smartharwareshop.productInventory.exception.InvalidSearchRegex;
import com.smartharwareshop.productInventory.model.ProductDescription;
import org.springframework.stereotype.Component;

@Component
public class InventoryOperationValidator {

  private InventoryOperationValidator() {}

  public static boolean validateProductDescription(ProductDescription description) {
    boolean throwException = false;
    String whatIsInvalid = "";
    if (description.name() != null && description.name().isBlank()) {
      throwException = true;
      whatIsInvalid = "Product Name";
    }
    if (description.categoryId() < 0) { // Future Scope, it will be validated against a categaory table in DB.
      throwException = true;
      whatIsInvalid += " Product Category";
    }
    if (description.price() < -1) { // Should be a valid Algo of checking the prices.
      throwException = true;
      whatIsInvalid += " Product Price";
    }
    if (throwException) throw new InvalidProductDescriptionException("Invalid!!, Following fields are invalid : " + whatIsInvalid);
    return true;
  }

  public static boolean isSearchRegexValid(String regex) {
    if (regex != null) {
      return true;
    }
    throw new InvalidSearchRegex("Invalid, string for performing product name filtering");

  }
}
