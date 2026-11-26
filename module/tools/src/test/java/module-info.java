// SPDX-FileCopyrightText: Copyright © 2024, 2026 Caleb Cushing
//
// SPDX-License-Identifier: PolyForm-Small-Business-1.0.0 OR PolyForm-Noncommercial-1.0.0

module com.xenoterracide.blackbox {
  requires com.xenoterracide.tools.java;
  requires org.junit.jupiter.api;
  requires org.assertj.core;
  requires io.vavr;
  opens com.xenoterracide.blackbox to org.junit.platform.commons;
}
