# Mybatis Generator

### I. Getting Started

1. Download this source
2. Config database information in file `database.properties`
  ```
  database.url=jdbc:{postgresql|oracle:thin}://localhost:port/database
  database.username=username
  database.password=password
  ```
3. Choose one of below codes and copy to `mybatis-generator.xml`
  ```xml
  <!-- For PostgreSQL -->
  <jdbcConnection driverClass="${database.postgresql-driver-class}"
      connectionURL="${database.url}"
      userId="${database.username}"
      password="${database.password}" />

  <!-- For Oracle -->
  <jdbcConnection driverClass="${database.oracle-driver-class}"
      connectionURL="${database.url}"
      userId="${database.username}"
      password="${database.password}" />
  ```
4. Generate tables
  ```xml
    <!-- This will generate MyTable.java (1) -->
    <table tableName="my_table" schema="ifHave" />

    <!-- This will generate Table.java (2) -->
    <table tableName="my_table" domainObjectName="table" />

    <!-- This will generate MyTable.java (3) -->
    <table tableName="my_table">
      <columnOverride column="my_column" property="column"/>
    </table>
  ```
  ```java
    // This will generate MyTable.java (1)
    class MyTable {
      private String myColumn;
    }

    // This will generate MyTable.java (2)
    class Table {
      private String myColumn;
    }

    // This will generate MyTable.java (3)
    class MyTable {
      private String column;
    }
  ```
5. Run command line
  ```
    mybatis-generator> mvn mybatis-generator:generate
  ```
### II. Advance Guide

  ```xml
  <!-- Auto generate annotation @Column and @Table -->
  <plugin type="com.mybatis.generator.plugin.PersistencePlugin"/>

  <!-- Auto generate lombok -->
  <plugin type="com.mybatis.generator.plugin.LombokPlugin">

    <!-- disable annotations -->
    <property name="getter" value="false"/>
    <property name="getter" value="false"/>

    <!-- enable annotations -->
    <property name="data" value="true"/>
    <property name="builder" value="true"/>
    <property name="toString" value="false"/>
    <property name="noArgsConstructor" value="true"/>
    <property name="allArgsConstructor" value="true"/>
  </plugin>

  <!-- Use java.time instead -->
  <javaTypeResolver>
    <property name="useJSR310Types" value="true"/>
  </javaTypeResolver>
  
  <!-- Auto generate @Mapper, maybe you don't need it -->
  <sqlMapGenerator
    targetPackage="com.mybatis.generator.mapper"
    targetProject="src/main/resources"/>
  <javaClientGenerator type="XMLMAPPER"
    targetPackage="com.mybatis.generator.mapper"
    targetProject="src/main/java"/>
  ```
### III. Reference
1. [Mybatis Generator](https://mybatis.org/generator/quickstart.html)
2. [Mybatis Generator Lombok](https://github.com/softwareloop/mybatis-generator-lombok-plugin)

### IV. Disadvantages
1. Can not generate composite key
2. Can not generate relationship tables