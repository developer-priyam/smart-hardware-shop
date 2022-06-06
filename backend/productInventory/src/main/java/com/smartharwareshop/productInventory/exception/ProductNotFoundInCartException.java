package com.smartharwareshop.productInventory.exception;

public class ProductNotFoundInCartException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public ProductNotFoundInCartException(String ex) {
    super(ex);
  }
}
