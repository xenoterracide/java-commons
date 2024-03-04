import org.jspecify.annotations.NullMarked;

/**
 * Tooling that I believe should be part of the JDK itself.
 *
 * @apiNote {@link org.jspecify.annotations.NullMarked} - {@link org.jspecify.annotations.NonNull} by default,
 * {@link org.jspecify.annotations.Nullable} will be documented. All public API's will be annotated explicitly.
 */
@NullMarked module com.xenoterracide.tools.java {
  requires java.base;
  requires org.jspecify;
  exports com.xenoterracide.tools0.java.function;
}
