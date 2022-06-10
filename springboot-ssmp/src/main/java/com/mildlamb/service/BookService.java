package com.mildlamb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mildlamb.pojo.Book;

import java.util.List;

public interface BookService {
    Boolean saveBook(Book book);
    Boolean updateBook(Book book);
    Boolean delBook(Integer id);
    Book selectBookById(Integer id);
    List<Book> selectBooks();
    IPage<Book> getPage(int currentPage,int pageSize);
}
