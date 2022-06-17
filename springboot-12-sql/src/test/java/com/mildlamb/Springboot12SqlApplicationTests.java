package com.mildlamb;

import com.mildlamb.dao.RoleDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot12SqlApplicationTests {

    @Autowired
    private RoleDao roleDao;

    @Test
    void contextLoads() {
        roleDao.selectById(1);
    }

}
