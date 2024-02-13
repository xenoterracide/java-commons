// SPDX-License-Identifier: Apache-2.0
// Copyright © 2024 Caleb Cushing.
package com.xenoterracide.tools0.java.annotation;

import jakarta.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
@Nonnull
public @interface NonNullFields {
}
