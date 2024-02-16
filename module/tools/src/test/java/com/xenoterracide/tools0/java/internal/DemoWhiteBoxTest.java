// SPDX-License-Identifier: Apache-2.0
// Copyright © 2024 Caleb Cushing.
package com.xenoterracide.tools0.java.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DemoWhiteBoxTest {

  @Test
  void testFoo() {
    assertEquals("foo", DemoWhiteBox.FOO);
  }
}
