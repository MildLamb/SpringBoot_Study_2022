package com.mildlamb;

import com.mildlamb.dao.RoleDao;
import com.mildlamb.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot04MybatisApplicationTests {

    @Autowired
    private RoleDao roleDao;

    @Test
    void contextLoads() {
        System.out.println(roleDao.getRolesById(1));
    }

}
