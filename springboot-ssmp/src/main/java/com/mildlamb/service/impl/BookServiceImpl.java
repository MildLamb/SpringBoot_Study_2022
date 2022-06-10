package com.mildlamb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mildlamb.dao.BookDao;
import com.mildlamb.pojo.Book;
import com.mildlamb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Boolean saveBook(Book book) {
        return bookDao.insert(book) > 0;
    }

    @Override
    public Boolean updateBook(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    public Boolean delBook(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public Book selectBookById(Integer id) {
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> selectBooks() {
        return bookDao.selectList(null);
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        return bookDao.selectPage(page,null);
    }
}
