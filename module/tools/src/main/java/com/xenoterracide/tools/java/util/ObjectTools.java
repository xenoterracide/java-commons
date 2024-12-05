// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.util;

import org.jspecify.annotations.Nullable;

/**
 * Utility methods for working with objects.
 */
public class ObjectTools {

  private ObjectTools() {}

  /**
   * Returns the given object if it is not null. Otherwise, throws an {@link IllegalStateException}.
   *
   * @param <T> the type of the object
   * @param o   the object
   * @return the object
   * @throws IllegalStateException if the object is null
   */
  public static <T> T illegalStateNull(@Nullable T o) {
    return illegalStateNull(o, "object is null");
  }

  /**
   * Returns the given object if it is not null. Otherwise, throws an {@link IllegalStateException}.
   *
   * @param <T>     the type of the object
   * @param o       the object
   * @param message the message to include in the exception
   * @return the object
   * @throws IllegalStateException if the object is null
   */
  public static <T> T illegalStateNull(@Nullable T o, String message) {
    if (o == null) {
      throw new IllegalStateException(message);
    }
    return o;
  }

  /**
   * Returns the given object if it is not null. Otherwise, throws an {@link IllegalArgumentException}.
   *
   * @param <T> the type of the object
   * @param o   the object
   * @return the object
   * @throws IllegalStateException if the object is null
   */
  public static <T> T illegalArgumentNull(@Nullable T o) {
    return illegalArgumentNull(o, "object is null");
  }

  /**
   * Returns the given object if it is not null. Otherwise, throws an {@link IllegalArgumentException}.
   *
   * @param <T>     the type of the object
   * @param o       the object
   * @param message the message to include in the exception
   * @return the object
   * @throws IllegalStateException if the object is null
   */
  public static <T> T illegalArgumentNull(@Nullable T o, String message) {
    if (o == null) {
      throw new IllegalArgumentException(message);
    }
    return o;
  }
}
