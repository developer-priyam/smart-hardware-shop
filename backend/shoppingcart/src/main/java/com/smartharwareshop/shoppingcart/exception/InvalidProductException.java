package com.smartharwareshop.shoppingcart.exception;

public class InvalidProductException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public InvalidProductException(String ex) {
    super(ex);
  }
}
