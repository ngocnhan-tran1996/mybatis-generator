package com.mybatis.generator.constant;

public final class Strings {

  public static final String DOT = ".";

  public static String addColumnAnnotation(String columnName) {

    return addAnnotation("Column", columnName);
  }

  public static String addTableAnnotation(String columnName) {

    return addAnnotation("Table", columnName);
  }

  private static String addAnnotation(String annotationName, String name) {

    return String.format("@%s(name = \"%s\")", annotationName, name);
  }

  public static boolean notEqualsIgnoreCase(String annotationName, String name) {

    return annotationName == null
        || !annotationName.equalsIgnoreCase(name);
  }

  private Strings() {
    // hide constructor
  }
}