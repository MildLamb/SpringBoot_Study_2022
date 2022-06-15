package com.mildlamb.daoTest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mildlamb.dao.BookDao;
import com.mildlamb.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SSMPApplicationDaoTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void test(){
        System.out.println(bookDao);
    }

    @Test
    void testSelectById() {
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSave(){
        Book book = new Book("一个测试数据","这是一个测试数据","一个用来测试的数据");
        bookDao.insert(book);
    }

    @Test
    void testSelectAll(){
        System.out.println(bookDao.selectList(null));
    }

    @Test
    void testDel(){
        bookDao.deleteById(1);
    }

    @Test
    // 分页功能需要使用MP提供的拦截器实现
    /**
     * param1 第几页
     * param2 每页显式多少数据
     */
    void testGetPage(){
        IPage page = new Page(1,5);
        bookDao.selectPage(page,null);
        // 获取当前指定的页数
        System.out.println(page.getCurrent());
        // 获取当前指定每页的大小
        System.out.println(page.getSize());
        // 获取数据总共有多少页
        System.out.println(page.getPages());
        // 获取所有数据的总个数
        System.out.println(page.getTotal());
        // 获取所有数据
        System.out.println(page.getRecords());
    }

    @Test
    void testGetByType(){
        QueryWrapper<Book> qw = new QueryWrapper<>();
        // 可以使用链式编程
        qw.like("name","spring");
        bookDao.selectList(qw);
    }

    @Test
    void testGetByType2(){
        String condition = "spring";
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        /**
         * param1 判断条件 如果前面的判断条件结果为true 则MP后续会在预编译的sql中连接后面的模糊查询
         * param2 得到的数据
         * param3 模糊查询的条件
         */
        lqw.like(condition!=null,Book::getName,condition);
        bookDao.selectList(lqw);
    }

}
