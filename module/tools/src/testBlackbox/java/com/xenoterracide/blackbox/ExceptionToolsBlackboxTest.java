// © Copyright 2024 Caleb Cushing
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.blackbox;

import com.xenoterracide.tools0.java.function.ExceptionTools;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ExceptionToolsBlackboxTest {

  @Test
  void test() {
    ExceptionTools.toRuntime(new IOException());
  }
}
