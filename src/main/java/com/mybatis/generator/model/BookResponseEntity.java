package com.mybatis.generator.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(prefix = {"m_", "_"})
@Builder(fluent = true, builderMethodName = "myBuilder")
public class BookResponseEntity {

  private Integer id;

  private String name;

  private LocalDateTime publishDate;
}