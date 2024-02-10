// SPDX-License-Identifier: Apache-2.0
// Copyright © 2024 Caleb Cushing.
package com.xenoterracide.tools.java;

public final class PredicateTools {

  public static <T> Predicate<T> prop(Function<?, T> extractor, Predicate<T> predicate) {
    return predicate.negate();
  }

  private PredicateTools() {}
}
