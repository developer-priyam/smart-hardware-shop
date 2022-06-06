package com.smartharwareshop.productInventory.exception;


public class InvalidSearchRegex extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public InvalidSearchRegex(String s) {
    super(s);
  }
}
