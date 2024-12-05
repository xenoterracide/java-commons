// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.blackbox;

import static org.assertj.core.api.Assertions.assertThat;

import com.xenoterracide.tools.java.collection.ArrayTools;
import java.util.Random;
import org.junit.jupiter.api.Test;

class ArrayToolsTest {

  @Test
  void fillFrom() {
    assertThat(
      ArrayTools.fillFrom(5, bytes -> {
        for (var i = 0; i < bytes.length; i++) {
          bytes[i] = (byte) i;
        }
      })
    ).containsExactly((byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4);

    // real world use case
    var random = new Random();
    assertThat(ArrayTools.fillFrom(5, random::nextBytes)).isNotEmpty();
  }
}
