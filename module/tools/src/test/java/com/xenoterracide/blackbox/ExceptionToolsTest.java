// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.blackbox;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.xenoterracide.tools.java.function.ExceptionTools;
import io.vavr.control.Try;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionToolsTest {

  @Test
  void convertRuntimeExceptionsAreJustRethrown() {
    Assertions.assertThat(ExceptionTools.toRuntime(new NullPointerException()))
      .isInstanceOf(NullPointerException.class)
      .hasNoCause();
  }

  @Test
  void convertIoExceptionsAsUncheckedIoExceptions() {
    var e = new IOException();
    assertThat(ExceptionTools.toRuntime(e)).isInstanceOf(UncheckedIOException.class).hasCause(e);
  }

  @Test
  void convertOtherCheckedAsRuntime() {
    var e = new NoSuchFieldException();
    assertThat(ExceptionTools.toRuntime(e)).isInstanceOf(RuntimeException.class).hasCause(e);
  }

  @Test
  void convertVavr() {
    assertThatExceptionOfType(UncheckedIOException.class).isThrownBy(() -> {
      Try.of(() -> {
        throw new IOException();
      }).getOrElseThrow(ExceptionTools::toRuntime);
    });
  }
}
