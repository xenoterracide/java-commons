// © Copyright 2024 Caleb Cushing
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.blackbox;

import static org.assertj.core.api.Assertions.assertThat;

import com.xenoterracide.tools0.java.function.ExceptionTools;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.Test;

class ExceptionToolsBlackboxTest {

  @Test
  void test() {
    assertThat(ExceptionTools.toRuntime(new IOException())).isInstanceOf(UncheckedIOException.class);
  }
}
