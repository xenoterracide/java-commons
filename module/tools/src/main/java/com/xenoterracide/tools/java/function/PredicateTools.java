// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.function;

import java.util.function.Function;
import java.util.function.Predicate;
import org.jspecify.annotations.NonNull;

/**
 * The type Predicate tools.
 */
public final class PredicateTools {

  private PredicateTools() {}

  /**
   * Allows for simple filtering based on a property of an object.
   * {@snippet :
   *   record TestRecord(String name) {}
   * }
   * {@snippet :
   *   Stream.of(new TestRecord("Caleb"), new TestRecord("Bob"))
   *     .filter(prop(TestRecord::name, Predicate.isEqual("Caleb")))
   *     .collect(Collectors.toList()); // [TestRecord[name=Caleb]]
   * }
   *
   * @param <T>       the type parameter
   * @param <PROP>    the type parameter
   * @param extractor the extractor
   * @param predicate the predicate
   * @return the predicate
   */
  public static @NonNull <T, PROP> Predicate<T> prop(
    @NonNull Function<T, PROP> extractor,
    @NonNull Predicate<PROP> predicate
  ) {
    return t -> predicate.test(extractor.apply(t));
  }
}
