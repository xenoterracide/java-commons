// SPDX-FileCopyrightText: Copyright © 2024 - 2025 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.function;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Utilities for working with exceptions.
 */
public final class ExceptionTools {

  private ExceptionTools() {}

  /**
   * Converts a checked exception to a {@link RuntimeException}.
   * {@snippet :
   *   try {
   *     throw new IOException();
   *   } catch (Exception e) {
   *     throw ExceptionTools.toRuntime(e);
   *   }
   *   // Try is from vavr.io
   *   Try.of(() -> { throw new IOException(); })
   *     .getOrElseThrow(ExceptionTools::toRuntime);
   * }
   *
   * @param e the exception to convert.
   * @return {@link RuntimeException} the converted exception.
   * @implNote
   *     <ul>
   *       <li>{@link IOException} to {@link UncheckedIOException}.</li>
   *       <li>{@link RuntimeException} is returned as is.</li>
   *       <li>Other exceptions are wrapped in a {@link RuntimeException}.</li>
   *     </ul>
   * @see <a href="https://docs.vavr.io/#_try">vavr.io Try</a>
   */
  // CHECKSTYLE.OFF: ReturnCount
  public static RuntimeException toRuntime(Throwable e) {
    if (e instanceof IOException ex) {
      return new UncheckedIOException(ex);
    }
    if (e instanceof RuntimeException ex) {
      return ex;
    }
    return new RuntimeException(e);
  }
  // CHECKSTYLE.ON
}
