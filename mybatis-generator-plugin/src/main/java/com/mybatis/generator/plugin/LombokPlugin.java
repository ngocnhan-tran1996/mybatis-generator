package com.mybatis.generator.plugin;

import com.mybatis.generator.annotation.LombokAnnotation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;


public class LombokPlugin extends PluginAdapter {

  private final List<LombokAnnotation> lombokAnnotations;

  public LombokPlugin() {
    lombokAnnotations = new ArrayList<>();
  }

  @Override
  public boolean validate(List<String> list) {
    return true;
  }

  @Override
  public boolean modelBaseRecordClassGenerated(
      TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    addAnnotations(topLevelClass);
    return true;
  }


  @Override
  public boolean modelGetterMethodGenerated(
      Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  @Override
  public boolean modelSetterMethodGenerated(
      Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  private void addAnnotations(TopLevelClass topLevelClass) {
    for (LombokAnnotation lombokAnnotation : lombokAnnotations) {
      topLevelClass.addImportedType(lombokAnnotation.getClassName());
      topLevelClass.addAnnotation(lombokAnnotation.getName());
    }
  }

  @Override
  public void setProperties(Properties properties) {
    super.setProperties(properties);

    properties.stringPropertyNames()
        .stream()
        .filter(propertyName -> Boolean.parseBoolean(properties.getProperty(propertyName)))
        .filter(LombokAnnotation::contains)
        .forEach(propertyName -> {

          var lombokAnnotation = LombokAnnotation.getByPropertyName(propertyName);
          lombokAnnotations.add(lombokAnnotation);
        });

    // @Data is default annotation
    if (lombokAnnotations.isEmpty()) {
      lombokAnnotations.add(LombokAnnotation.DATA);
    }

    lombokAnnotations.sort(Comparator.comparing(LombokAnnotation::getPropertyName));
  }
}