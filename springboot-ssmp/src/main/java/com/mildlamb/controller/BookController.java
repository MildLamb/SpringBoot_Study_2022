package com.mildlamb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mildlamb.pojo.Book;
import com.mildlamb.service.BookService;
import com.mildlamb.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService iBookService;

    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return iBookService.list();
    }

    @PostMapping("/saveBook")
    public boolean saveBook(@RequestBody Book book){
        return iBookService.save(book);
    }

    @PutMapping("/updateBook")
    public Boolean update(@RequestBody Book book){
        return iBookService.updateById(book);
    }

    @DeleteMapping("/delBook/{bid}")
    public Boolean deleteBook(@PathVariable("bid") Integer id){
        return iBookService.removeById(id);
    }

    @GetMapping("/getBook/{bid}")
    public Book getBookById(@PathVariable("bid") Integer id){
        return iBookService.getById(id);
    }

    @GetMapping("/getPage/{current}/{size}")
    public IPage<Book> getPageBooks(@PathVariable("current") int currentPage,@PathVariable("size") int pageSize){
        return iBookService.getPage(currentPage,pageSize);
    }
}
