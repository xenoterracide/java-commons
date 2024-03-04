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
   * Converts a checked exception to an unchecked exception.
   *
   * - {@link IOException} to {@link UncheckedIOException}.
   * - {@link RuntimeException} is returned as is.
   *
   * [source,java]
   * --
   * class Sample {
   *   void vavrSample() {
   *     Try.of(() -> { throw new IOException(); }).getOrElseThrow(ExceptionTools::convert);
   *   }
   *
   *   void rethrowExample() {
   *     try {
   *       throw new IOException();
   *     } catch (Exception e) {
   *       throw ExceptionTools.convert(e);
   *     }
   *   }
   * }
   * --
   *
   * @param  e the exception to convert
   */
  // CHECKSTYLE:OFF: ReturnCount - library to avoid conditional returns and throws elsewhere
  public static @NonNull RuntimeException convert(@NonNull Throwable e) {
    if (e instanceof IOException) {
      return new UncheckedIOException((IOException) e);
    }
    if (e instanceof RuntimeException) {
      return (RuntimeException) e;
    }
    return new RuntimeException(e);
  }
  // CHECKSTYLE:ON
}
