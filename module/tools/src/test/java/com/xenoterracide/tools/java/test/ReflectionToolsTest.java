// © Copyright 2024 Caleb Cushing
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.xenoterracide.tools.java.reflect.ReflectionTools;
import org.junit.jupiter.api.Test;

class ReflectionToolsTest {

  @Test
  void readField() {
    assertThat(ReflectionTools.readField(new TestObject(), "field", String.class)).isEqualTo("value");
  }

  class TestObject {

    String field = "value";
  }
}
