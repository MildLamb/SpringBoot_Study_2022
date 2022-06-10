# 基于SpringBoot的SSMP整合案例
## MybatisPlus实现分页功能
- 分页操作需要我们设定分页对象IPage
```java
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
```
- 配置MybatisPlus拦截器
```java
@Configuration
public class MPConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // 1. 定义MP拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 2. 添加具体的拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
```