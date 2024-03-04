import org.jspecify.annotations.NullMarked;

/**
 * Tooling that I believe should be part of the JDK itself.
 */
@NullMarked module com.xenoterracide.tools.java {
  requires java.base;
  requires org.jspecify;
  exports com.xenoterracide.tools0.java.function;
}
