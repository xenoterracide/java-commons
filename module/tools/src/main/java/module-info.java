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
}
