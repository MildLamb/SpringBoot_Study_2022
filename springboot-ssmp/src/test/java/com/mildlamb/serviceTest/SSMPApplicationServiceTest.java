package com.mildlamb.serviceTest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mildlamb.pojo.Book;
import com.mildlamb.service.BookService;
import com.mildlamb.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SSMPApplicationServiceTest {
    @Autowired
    private IBookService bookService;

    @Test
    void test(){
        System.out.println(bookService);
    }

    @Test
    void testGetBookById(){
        System.out.println(bookService.getById(1));
    }

    @Test
    void testSaveBook(){
        Book book = new Book("死亡笔记","死灵之书","你说千珏看了会说什么");
        bookService.save(book);
    }

    @Test
    void testUpdateBook(){
        Book book = new Book(1,"死亡笔记","死灵之书","你说什么!");
        bookService.updateById(book);
    }

    @Test
    void testDelBook(){
        bookService.removeById(6);
    }

    @Test
    void testGetBookByPage(){
        IPage<Book> mp_page = new Page(1, 5);
        IPage<Book> bookIPage = bookService.page(mp_page);
        System.out.println(bookIPage);
    }

    @Test
    void testGetAllBook(){
        System.out.println(bookService.list());
    }
}
