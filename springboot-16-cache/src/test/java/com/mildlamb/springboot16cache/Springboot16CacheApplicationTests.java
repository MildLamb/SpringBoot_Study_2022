package com.mildlamb.springboot16cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot16CacheApplicationTests {

        @Test
        void test1() {
            // 1. 创建缓存管理器
            CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");

            // 2. 获取缓存对象
            Cache cache = cacheManager.getCache("cloud_user");

            // 3. 创建元素
            Element element = new Element("key1", "value1");

            // 4. 将元素添加到缓存
            cache.put(element);

            // 5. 获取缓存
            Element value = cache.get("key1");
            System.out.println(value);
            System.out.println(value.getObjectValue());

            // 6. 删除元素
            cache.remove("key1");

            System.out.println(cache.getSize());

            // 7. 刷新缓存
            cache.flush();

            // 8. 关闭缓存管理器
            cacheManager.shutdown();

        }

    @Test
    void contextLoads() {
    }

}
