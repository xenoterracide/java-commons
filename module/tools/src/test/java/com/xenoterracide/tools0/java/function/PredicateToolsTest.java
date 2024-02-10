// SPDX-License-Identifier: Apache-2.0
// Copyright © 2024 Caleb Cushing.
package com.xenoterracide.tools0.java.function;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class PredicateToolsTest {

  @Test
  void prop() {
    var record = new TestRecord("Caleb", 30);
    var pred = PredicateTools.prop(TestRecord::name, Predicate.isEqual("Caleb"));
    assertThat(pred.test(record)).isTrue();
  }

  record TestRecord(String name, int age) {}
}
