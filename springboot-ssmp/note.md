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

## MP实现数据层快速开发
- 定义接口实现BaseMapper接口
```java
@Mapper
public interface BookDao extends BaseMapper<Book> {
}
```

## MP实现业务层快速开发
- 使用MP提供的业务层通用接口(IService)与业务层通用实现类(ServiceImpl<M,T>)
- 通用类基础上做功能重载或功能追加
- 注意重载时不要覆盖原始操作，避免原始提供的功能丢失

- 业务层接口实现IService接口
```java
public interface IBookService extends IService<Book> {
}
```
- 业务层接口实现类实现上方定义的接口并继承 ServiceImpl<M,T> , M为数据层接口，T为数据实体类
```java
@Service
public class MPBookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {
}
```