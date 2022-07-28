package com.mybatis.generator.constant;

public final class Strings {

  public static String addColumnAnnotation(String columnName) {

    return addAnnotation("Column", columnName);
  }

  public static String addTableAnnotation(String tableName, String schema) {

    String table = schema == null || schema.isBlank()
        ? tableName
        : String.format("%s, schema = \"%s", tableName, schema);

    return addAnnotation("Table", table);
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