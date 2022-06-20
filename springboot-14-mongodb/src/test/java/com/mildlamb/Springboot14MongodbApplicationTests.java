package com.mildlamb;

import com.mildlamb.pojo.Role;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;
import java.util.Queue;

@SpringBootTest
class Springboot14MongodbApplicationTests {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        Role role = new Role(1,"kindred",1500);
        mongoTemplate.save(role);
    }

    @Test
    void testSelectMongo(){
        Query query = new Query();
        query.comment("kindred");
        List<Role> list = mongoTemplate.find(query,Role.class);
        System.out.println(list);
    }

}
