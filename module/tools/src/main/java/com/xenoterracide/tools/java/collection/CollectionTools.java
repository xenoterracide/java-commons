// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.collection;

import java.util.Collection;
import java.util.function.Predicate;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Provides utility methods for working with collections.
 */
public final class CollectionTools {

  private CollectionTools() {}

  /**
   * Adds an element to a collection if a condition is met.
   *
   * @param collection the collection to add to
   * @param element the element to add
   * @param condition the condition to test
   * @param <T> the type of the element
   */
  public static <T> void addIf(
    @Nullable Collection<? super T> collection,
    @Nullable T element,
    @NonNull Predicate<Collection<? super T>> condition
  ) {
    if (collection == null) {
      return;
    }
    if (condition.test(collection)) {
      collection.add(element);
    }
  }
}
