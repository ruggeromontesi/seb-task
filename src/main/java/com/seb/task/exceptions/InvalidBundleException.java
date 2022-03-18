package com.seb.task.exceptions;

public class InvalidBundleException  extends RuntimeException {
   private static final long serialVersionUID = 1L;

   private String typedBundle;

   public InvalidBundleException(String message, String typedBundle) {
      super(message);
      this.typedBundle = typedBundle;

   }

   public String getTypedBundle() {
      return typedBundle;
   }
}
