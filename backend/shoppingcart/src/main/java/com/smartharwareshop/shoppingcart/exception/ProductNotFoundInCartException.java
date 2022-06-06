package com.smartharwareshop.shoppingcart.exception;

public class ProductNotFoundInCartException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public ProductNotFoundInCartException(String ex) {
    super(ex);
  }
}
