// SPDX-License-Identifier: Apache-2.0
// © Copyright 2024 Caleb Cushing. All rights reserved.

package com.xenoterracide.tools0.java.function;

import java.io.IOException;
import java.io.UncheckedIOException;
import org.jspecify.annotations.NonNull;

/**
 * Utilities for working with exceptions.
 */
public final class ExceptionTools {

  private ExceptionTools() {}

  /**
   * Rethrow the given exception as a runtime exception.
   * {@link RuntimeException} is simply rethrown.
   * {@link IOException} is rethrown as {@link UncheckedIOException}.
   * {@link Throwable} is rethrown as {@link RuntimeException}.
   * @param e the {@link Throwable} to rethrow
   */
  public static void rethrow(@NonNull Throwable e) {
    if (e instanceof IOException) {
      throw new UncheckedIOException((IOException) e);
    }
    if (e instanceof RuntimeException) {
      throw (RuntimeException) e;
    }
    throw new RuntimeException(e);
  }
}
