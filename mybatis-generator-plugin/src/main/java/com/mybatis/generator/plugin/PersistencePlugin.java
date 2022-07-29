package com.mybatis.generator.plugin;

import com.mybatis.generator.constant.Strings;
import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.JavaBeansUtil;

public class PersistencePlugin extends PluginAdapter {

  private boolean shouldAddTableAnnotation = false;
  private boolean shouldAddColumnAnnotation = false;
  private String tableAnnotationName;

  @Override
  public boolean validate(List<String> list) {
    return true;
  }

  @Override
  public boolean modelBaseRecordClassGenerated(
      TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {

    topLevelClass.addImportedType("javax.persistence.Entity");
    topLevelClass.addAnnotation("@Entity");

    if (shouldAddTableAnnotation) {
      topLevelClass.addImportedType("javax.persistence.Table");
      topLevelClass.addAnnotation(tableAnnotationName);
    }

    if (shouldAddColumnAnnotation) {
      topLevelClass.addImportedType("javax.persistence.Column");
    }

    return true;
  }

  @Override
  public boolean modelFieldGenerated(
      Field field,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {

    /* Table Annotation */
    String schema = introspectedTable.getTableConfiguration().getSchema();
    String tableName = introspectedTable.getTableConfiguration().getTableName();
    String camelCaseTableName = JavaBeansUtil.getCamelCaseString(tableName, false);
    String actualTableName = introspectedTable.getTableConfiguration().getDomainObjectName();
    shouldAddTableAnnotation = actualTableName != null
        && Strings.notEqualsIgnoreCase(camelCaseTableName, actualTableName);
    tableAnnotationName = Strings.addTableAnnotation(tableName, schema);

    /* Column Annotation */
    String actualColumnName = introspectedColumn.getActualColumnName();
    String camelCaseActualColumnName = JavaBeansUtil.getCamelCaseString(actualColumnName, false);
    String columnName = introspectedColumn.getJavaProperty();

    if (Strings.notEqualsIgnoreCase(camelCaseActualColumnName, columnName)) {
      field.addAnnotation(Strings.addColumnAnnotation(actualColumnName));
      shouldAddColumnAnnotation = true;
    }

    return super.modelFieldGenerated(
        field,
        topLevelClass,
        introspectedColumn,
        introspectedTable,
        modelClassType);
  }
}