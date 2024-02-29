// SPDX-License-Identifier: Apache-2.0
// © Copyright 2024 Caleb Cushing. All rights reserved.

package com.xenoterracide.tools0.java.function;

import java.util.function.Function;
import java.util.function.Predicate;
import org.jspecify.annotations.NonNull;

public final class PredicateTools {

  private PredicateTools() {}

  public static @NonNull <T, PROP> Predicate<T> prop(
    @NonNull Function<T, PROP> extractor,
    @NonNull Predicate<PROP> predicate
  ) {
    return t -> predicate.test(extractor.apply(t));
  }
}
