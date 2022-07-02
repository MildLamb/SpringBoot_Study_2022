package com.mildlamb.controller;

import cn.engulf.service.IpCountService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mildlamb.controller.utils.ReturnData;
import com.mildlamb.pojo.Book;
import com.mildlamb.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
    private IBookService iBookService;

//    @Autowired
//    private IpCountService ipCountService;

    @GetMapping("/all")
    public ReturnData getAllBooks(){
        return new ReturnData(true,iBookService.list());
    }

    @PostMapping("/saveBook")
    public ReturnData saveBook(@RequestBody Book book) throws IOException {
        if (book.getName().equals("123")) throw new IOException();
        boolean flag = iBookService.save(book);
        return new ReturnData(flag?"操作成功^_^":"操作失败-_-",flag);
    }

    @PutMapping("/updateBook")
    public ReturnData update(@RequestBody Book book){
        return new ReturnData(iBookService.updateById(book));
    }

    @DeleteMapping("/delBook/{bid}")
    public ReturnData deleteBook(@PathVariable("bid") Integer id){
        return new ReturnData(iBookService.removeById(id));
    }

    @GetMapping("/getBook/{bid}")
    public ReturnData getBookById(@PathVariable("bid") Integer id){
        return new ReturnData(iBookService.getById(id) != null ? true : false,iBookService.getById(id));
    }

//    @GetMapping("/getPage/{current}/{size}")
//    public ReturnData getPageBooks(@PathVariable("current") int currentPage,@PathVariable("size") int pageSize){
//        IPage<Book> page = iBookService.getPage(currentPage, pageSize);
//        if (currentPage > page.getPages()){
//            page = iBookService.getPage((int)page.getPages(), pageSize);
//        }
//        return new ReturnData(page != null ? true : false,page);
//    }

    @GetMapping("/getPage/{current}/{size}")
    public ReturnData getPageBooks(@PathVariable("current") int currentPage,@PathVariable("size") int pageSize,Book book){

        System.out.println("Book ==> " + book);

        IPage<Book> page = iBookService.getPage(currentPage, pageSize,book);
        if (currentPage > page.getPages()){
            page = iBookService.getPage((int)page.getPages(), pageSize,book);
        }
        return new ReturnData(page != null ? true : false,page);
    }
}
