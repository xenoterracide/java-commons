// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.blackbox;

import static com.xenoterracide.tools.java.function.PredicateTools.is;
import static java.util.function.Predicate.isEqual;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class PredicateToolsTest {

  @Test
  void isTest() {
    var t = new TestRecord("Caleb");

    var isEqual = is(TestRecord::name, isEqual(t.name())).test(t);
    var isNotEqual = is(TestRecord::name, isEqual(t.name()).negate()).test(t);

    assertThat(isEqual).isTrue();
    assertThat(isNotEqual).isFalse();
  }

  @Test
  @SuppressWarnings("NullAway")
  void isArgNulls() {
    var t = new TestRecord("Caleb");

    var extractorNull = is(null, isEqual(null));
    var predNull = is(TestRecord::name, null);

    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> extractorNull.test(t));
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> predNull.test(t));
  }

  record TestRecord(String name) {}
}
