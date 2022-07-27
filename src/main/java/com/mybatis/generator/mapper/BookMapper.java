package com.mybatis.generator.mapper;

import com.mybatis.generator.model.Book;
import java.util.List;

public interface BookMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Book row);

  Book selectByPrimaryKey(Integer id);

  List<Book> selectAll();

  int updateByPrimaryKey(Book row);
}