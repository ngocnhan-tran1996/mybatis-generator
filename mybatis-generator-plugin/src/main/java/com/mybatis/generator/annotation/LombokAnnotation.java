package com.mybatis.generator.annotation;

import java.util.Arrays;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public enum LombokAnnotation {

  DATA(
      "data",
      "@Data",
      "lombok.Data"),

  BUILDER(
      "builder",
      "@Builder",
      "lombok.Builder"),

  ALL_ARGS_CONSTRUCTOR(
      "allArgsConstructor",
      "@AllArgsConstructor",
      "lombok.AllArgsConstructor"),

  NO_ARGS_CONSTRUCTOR(
      "noArgsConstructor",
      "@NoArgsConstructor",
      "lombok.NoArgsConstructor"),

  GETTER(
      "getter",
      "@Getter",
      "lombok.Getter"),

  SETTER(
      "setter",
      "@Setter",
      "lombok.Setter"),

  TO_STRING(
      "toString",
      "@ToString",
      "lombok.ToString");


  private final String propertyName;
  private final String name;
  private final FullyQualifiedJavaType className;

  LombokAnnotation(String propertyName, String name, String className) {
    this.propertyName = propertyName;
    this.name = name;
    this.className = new FullyQualifiedJavaType(className);
  }

  /**
   * Check the name attribute in tag property exists or not
   *
   * @param propertyName the name attribute in tag property
   * @return true if {@code propertyName} exists in {@link LombokAnnotation#values()}, otherwise
   * return false
   */
  public static boolean contains(String propertyName) {
    return getByPropertyName(propertyName) != null;
  }

  public static LombokAnnotation getByPropertyName(String propertyName) {

    return Arrays.stream(LombokAnnotation.values())
        .filter(annotation -> annotation.propertyName.equalsIgnoreCase(propertyName))
        .findFirst()
        .orElse(null);
  }

  public String getPropertyName() {
    return this.propertyName;
  }

  public String getName() {
    return this.name;
  }

  public FullyQualifiedJavaType getClassName() {
    return this.className;
  }
}