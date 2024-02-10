// SPDX-License-Identifier: Apache-2.0
// Copyright © 2024 Caleb Cushing.
package com.xenoterracide.tools0.java.function;

import java.util.function.Function;
import java.util.function.Predicate;

public final class PredicateTools {

  private PredicateTools() {}

  public static <T, PROP> Predicate<T> prop(Function<T, PROP> extractor, Predicate<PROP> predicate) {
    return t -> predicate.test(extractor.apply(t));
  }
}
