// SPDX-License-Identifier: Apache-2.0
// © Copyright 2024 Caleb Cushing. All rights reserved.

package com.xenoterracide.tools0.java.function;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.IOException;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.Test;

class ExceptionToolsTest {

  @Test
  void rethrowRuntimeExceptionsAreJustRethrown() {
    assertThatExceptionOfType(NullPointerException.class)
      .isThrownBy(() -> ExceptionTools.rethrow(new NullPointerException()))
      .withNoCause();
  }

  @Test
  void rethrowIoExceptionsAsUncheckedIoExceptions() {
    var e = new IOException();
    assertThatExceptionOfType(UncheckedIOException.class).isThrownBy(() -> ExceptionTools.rethrow(e)).withCause(e);
  }

  @Test
  void rethrowOtherCheckedAsRuntime() {
    var e = new NoSuchFieldException();
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> ExceptionTools.rethrow(e)).withCause(e);
  }
}
