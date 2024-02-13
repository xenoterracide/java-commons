// SPDX-License-Identifier: Apache-2.0
// Copyright © 2024 Caleb Cushing.
package com.xenoterracide.tools0.java.function;

import static com.xenoterracide.tools0.java.function.PredicateTools.prop;
import static java.util.function.Predicate.isEqual;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class PredicateToolsTest {

  @Test
  void propTest() {
    var t = new TestRecord("Caleb");

    var isEqual = prop(TestRecord::name, isEqual(t.name())).test(t);
    var isNotEqual = prop(TestRecord::name, isEqual(t.name()).negate()).test(t);

    assertThat(isEqual).isTrue();
    assertThat(isNotEqual).isFalse();
  }

  @Test
  @SuppressWarnings("NullAway")
  void propArgNulls() {
    var t = new TestRecord("Caleb");

    var extractorNull = prop(null, isEqual(null));
    var predNull = prop(TestRecord::name, null);

    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> extractorNull.test(t));
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> predNull.test(t));
  }

  record TestRecord(String name) {}
}
