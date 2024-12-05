// SPDX-FileCopyrightText: Copyright © 2024 Caleb Cushing
//
// SPDX-License-Identifier: Apache-2.0

import org.jspecify.annotations.NullMarked;

/**
 * Tooling that I believe should be part of the JDK itself.
 * <p>
 *   Module is {@link NullMarked} and APIs {@link org.jspecify.annotations.NonNull} unless otherwise documented.
 * </p>
 */
@NullMarked module com.xenoterracide.tools.java {
  requires static org.jspecify;
  requires java.base;
  exports com.xenoterracide.tools.java.function;
  exports com.xenoterracide.tools.java.annotation;
  exports com.xenoterracide.tools.java.collection;
  exports com.xenoterracide.tools.java.util;
}
