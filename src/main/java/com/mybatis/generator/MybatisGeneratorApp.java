package com.mybatis.generator;

import java.io.IOException;

public class MybatisGeneratorApp {

  public static void main(String[] args) throws IOException {
    Runtime.getRuntime().exec("cmd.exe /c mvn clean install");
  }
}