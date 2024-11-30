// © Copyright 2024 Caleb Cushing
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.xenoterracide.tools.java.collection.CollectionTools;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class CollectionToolsTest {

  @Test
  void testAddIf() {
    var list = new ArrayList<String>();
    CollectionTools.addIf(list, "foo", Collection::isEmpty);
    CollectionTools.addIf(list, "bar", Collection::isEmpty);
    assertThat(list).hasSize(1).containsExactly("foo");

    CollectionTools.addIf(list, "foo", c -> !c.contains("foo"));
    CollectionTools.addIf(list, "bar", c -> !c.contains("bar"));
    assertThat(list).hasSize(2).containsExactly("foo", "bar");

    CollectionTools.addIf(list, null, Collection::isEmpty);

    assertThatNoException().isThrownBy(() -> CollectionTools.addIf(null, 1L, Collection::isEmpty));

    var set = new HashSet<Long>();
    CollectionTools.addIf(set, null, Collection::isEmpty);
    assertThat(set).hasSize(1).containsOnlyNulls();
  }
}
