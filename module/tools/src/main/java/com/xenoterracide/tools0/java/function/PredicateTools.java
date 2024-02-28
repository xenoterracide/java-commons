// SPDX-License-Identifier: Apache-2.0
// © Copyright 2024 Caleb Cushing. All rights reserved.

package com.xenoterracide.tools0.java.function;

import jakarta.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Predicate;

public final class PredicateTools {

  private PredicateTools() {}

  public static @Nonnull <T, PROP> Predicate<T> prop(
    @Nonnull Function<T, PROP> extractor,
    @Nonnull Predicate<PROP> predicate
  ) {
    return t -> predicate.test(extractor.apply(t));
  }
}
