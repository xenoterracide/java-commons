// © Copyright 2024 Caleb Cushing
// SPDX-License-Identifier: Apache-2.0

package com.xenoterracide.tools.java.reflect;

import com.xenoterracide.tools.java.exception.UncheckedReflectionException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.jspecify.annotations.Nullable;

public final class ReflectionTools {

  private static final Map<Class<?>, Stream<Field>> FIELDS_CACHE = new ConcurrentHashMap<>();

  private ReflectionTools() {}

  /**
   * Reads the value of a field from an object.
   *
   * @param target     the object to read the field from
   * @param fieldName  the name of the field to read
   * @param returnType the type of the field
   * @param <T>        the type of the field
   * @return the value of the field
   * @throws UncheckedReflectionException if the field cannot be read
   * @throws ClassCastException           if the field value cannot be cast to the return type
   */
  public static <T> @Nullable T readField(Object target, String fieldName, Class<T> returnType)
    throws UncheckedReflectionException, ClassCastException {
    var targetClass = target.getClass();

    var targetFields = FIELDS_CACHE.computeIfAbsent(targetClass, c -> {
      try {
        return Stream.of(targetClass.getFields());
      } catch (SecurityException e) {
        throw new UncheckedReflectionException(e);
      }
    });

    return targetFields
      .filter(f -> Objects.equals(f.getName(), fieldName))
      .peek(Field::trySetAccessible)
      .filter(f -> f.canAccess(target))
      .findAny()
      .map(f -> {
        try {
          return f.get(target);
        } catch (IllegalAccessException e) {
          throw new UncheckedReflectionException(e);
        }
      })
      .map(returnType::cast)
      .orElse(null);
  }
}
