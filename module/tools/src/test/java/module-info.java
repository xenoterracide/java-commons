module com.xenoterracide.blackbox {
  requires com.xenoterracide.tools.java;
  requires org.junit.jupiter.api;
  requires org.assertj.core;
  requires io.vavr;
  opens com.xenoterracide.blackbox to org.junit.platform.commons;
}
