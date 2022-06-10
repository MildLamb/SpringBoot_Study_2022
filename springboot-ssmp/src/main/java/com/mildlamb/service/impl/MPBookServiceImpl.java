package com.mildlamb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mildlamb.dao.BookDao;
import com.mildlamb.pojo.Book;
import com.mildlamb.service.IBookService;
import org.springframework.stereotype.Service;

/**
 * MP实现业务层接口
 *  - 实现类实现业务层接口，同时继承 ServiceImpl 类，提供两个泛型.
 *      - 第一个泛型是：数据层接口
 *      - 第二个泛型是：封装所需的实体类
 */

@Service
public class MPBookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {
}
