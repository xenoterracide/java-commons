module com.xenoterracide {
  exports com.xenoterracide;
  requires spring.boot.autoconfigure;
  opens com.xenoterracide to spring.core;
}
