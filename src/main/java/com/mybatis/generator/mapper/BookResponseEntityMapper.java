package com.mybatis.generator.mapper;

import com.mybatis.generator.model.BookResponseEntity;
import java.util.List;

public interface BookResponseEntityMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(BookResponseEntity row);

  BookResponseEntity selectByPrimaryKey(Integer id);

  List<BookResponseEntity> selectAll();

  int updateByPrimaryKey(BookResponseEntity row);
}