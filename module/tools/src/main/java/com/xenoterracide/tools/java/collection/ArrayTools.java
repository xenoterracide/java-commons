// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.collection;

import java.util.function.Consumer;

/**
 * Provides utility methods for working with arrays.
 */
public final class ArrayTools {

  private ArrayTools() {}

  /**
   * Creates a byte array of a given size and allows a consumer to take that byte array then producing the filled array.
   *
   * @param size   size of the byte array
   * @param setter the consumer that can fill the array
   * @return the filled array
   */
  public static byte[] fillFrom(int size, Consumer<byte[]> setter) {
    var bytes = new byte[size];
    setter.accept(bytes);
    return bytes;
  }
}
