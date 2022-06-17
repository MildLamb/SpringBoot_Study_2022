package com.mildlamb;

import com.mildlamb.controller.RoleController;
import com.mildlamb.pojo.Role;
import com.mildlamb.pojo.RoleTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest

// spring事务的注解
@Transactional  // 默认事务是回滚的
//@Rollback(value = false)  // 关闭回滚
public class DBTest {

    @Autowired
    private RoleTestData roleTestData;

    @Autowired
    private RoleController controller;

    @Test
    void testController(){
        Role role = controller.getRole(1);
        System.out.println(role);
    }

    @Test
    void testSave(){
        controller.saveRole("千珏",1500);
    }

    @Test
    void test(){
        System.out.println(roleTestData);
    }
}
