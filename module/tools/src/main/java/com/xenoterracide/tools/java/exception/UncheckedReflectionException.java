// © Copyright 2024 Caleb Cushing
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.exception;

/**
 * An unchecked exception that wraps a checked exception thrown during reflection.
 */
public class UncheckedReflectionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UncheckedReflectionException(Throwable cause) {
    super(cause);
  }
}
