import org.jspecify.annotations.NullMarked;

/**
 * Tooling that I believe should be part of the JDK itself.
 * <p>
 *   Module is {@link NullMarked} and APIs {@link org.jspecify.annotations.NonNull} unless otherwise documented.
 * </p>
 */
@NullMarked module com.xenoterracide.tools {
  requires java.base;
  requires org.jspecify;
  exports com.xenoterracide.tools0.java.function;
}
