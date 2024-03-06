// SPDX-License-Identifier: Apache-2.0
// © Copyright 2024 Caleb Cushing. All rights reserved.

package com.xenoterracide.jpa;

import jakarta.validation.constraints.Pattern;
import org.jspecify.annotations.Nullable;

/**
 * An entity that has an identifier.
 *
 * @param <ID> the type of the identifier.
 */
public interface Identifiable<ID> {
  /**
   * Returns the identifier of this entity.
   *
   * @return the identifier of this entity. Nullable, but not blank.
   */
  @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
  @Nullable
  ID getId();
}
