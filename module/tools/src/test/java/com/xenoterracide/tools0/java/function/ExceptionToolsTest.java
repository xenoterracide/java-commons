// SPDX-License-Identifier: Apache-2.0
// © Copyright 2024 Caleb Cushing. All rights reserved.

package com.xenoterracide.tools0.java.function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import io.vavr.control.Try;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.Test;

class ExceptionToolsTest {

  @Test
  void convertRuntimeExceptionsAreJustRethrown() {
    assertThat(ExceptionTools.convert(new NullPointerException()))
      .isInstanceOf(NullPointerException.class)
      .hasNoCause();
  }

  @Test
  void convertIoExceptionsAsUncheckedIoExceptions() {
    var e = new IOException();
    assertThat(ExceptionTools.convert(e)).isInstanceOf(UncheckedIOException.class).hasCause(e);
  }

  @Test
  void convertOtherCheckedAsRuntime() {
    var e = new NoSuchFieldException();
    assertThat(ExceptionTools.convert(e)).isInstanceOf(RuntimeException.class).hasCause(e);
  }

  @Test
  void convertVavr() {
    assertThatExceptionOfType(UncheckedIOException.class).isThrownBy(() -> {
      Try.of(() -> {
        throw new IOException();
      }).getOrElseThrow(ExceptionTools::convert);
    });
  }
}
