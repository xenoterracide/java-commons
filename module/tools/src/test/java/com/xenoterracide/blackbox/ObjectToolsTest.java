// SPDX-FileCopyrightText: Copyright © 2024, 2026 Caleb Cushing
//
// SPDX-License-Identifier: PolyForm-Small-Business-1.0.0 OR PolyForm-Noncommercial-1.0.0

package com.xenoterracide.blackbox;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.xenoterracide.tools.java.util.ObjectTools;
import org.junit.jupiter.api.Test;

class ObjectToolsTest {

  @Test
  void illegalStateNull() {
    assertThat(ObjectTools.illegalStateNull("test")).isEqualTo("test");
    assertThatExceptionOfType(IllegalStateException.class)
      .isThrownBy(() -> ObjectTools.illegalStateNull(null))
      .withMessage("object is null");
  }

  @Test
  void illegalArgumentNull() {
    assertThat(ObjectTools.illegalArgumentNull("test")).isEqualTo("test");
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> ObjectTools.illegalArgumentNull(null))
      .withMessage("object is null");
  }
}
