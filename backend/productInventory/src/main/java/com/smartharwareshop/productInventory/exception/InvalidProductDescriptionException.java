package com.smartharwareshop.productInventory.exception;

public class InvalidProductDescriptionException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public InvalidProductDescriptionException(String ex) {
    super(ex);
  }
}
