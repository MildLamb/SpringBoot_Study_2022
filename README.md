# 学习Springboot
### Idea卡在Resolving Maven dependencies的解决方案
setting -> maven -> importing -> VM options for importer 添加 -Xms1024m -Xmx2048m

### 每次创建springboot项目的时候，都会有什么.mvm , HELP.md 等一些不必要的文件的解决
setting -> Editor ->  File Types -> Ignored Files and Folders

<hr>

# REST风格
### REST简介
- REST(Representational State Transfer)，表现形式状态转换
  - 传统风格资源描述形式
    - http://localhost/user/getById?id=1
    - http://localhost/user/saveUser
  - REST风格描述形式
    - http://localhost/user/1
    - http://localhost/user
- 优点：
  - 隐藏资源的访问行为，无法通过地址得知对资源是何种操作
  - 书写简化

### @RequestBody,@RequestParam和PathVariable的不同使用场景
- 区别：
  - @RequestParam用于接收url地址传参或表单传参
  - @RequestBody用于接收json数据
  - @PathVariable用于接收路径参数，使用{参数名称}描述路径参数
- 应用：
  - 如果接收的数据以json为主，尽量使用@RequestBody
  - 如果发送非json格式数据，选用@RequestParam接收请求参数
  - 采用RESTful进行开发，当参数数量较少时，可以使用@PathVariable接收请求路径变量

### @RestController
- 包含 @Controller 和 @ResponseBody

<hr>

## 基础配置
- SpringBoot中导入对应starter后，提供对应配置属性

### 配置文件优先级
properties > yml > yaml

## yaml数据格式
- yaml(YAML Ain't Markup Language)，一种数据序列化格式
- 优点：
  - 容易阅读
  - 容易与脚本语言交互
  - 以数据为核心，重数据轻格式
- YAML文件扩展名
  - .yml(主流)
  - .yaml
- yaml语法规则
  - 大小写敏感
  - 属性层级关系使用多行描述，每行结尾使用冒号结束
  - 使用缩进表示层级关系，同层级左侧对齐，只允许使用空格，不允许使用Tab键(IDEA能用是因为做了处理)
  - 属性值前面加空格(属性名与属性值之间使用 冒号+空格 作为分隔符)
  - \# 表示注释

## 读取Springboot配置中的数据
- 使用@Value配置SpEL读取单个数据
- 使用Environment类的实例对象获取属性，getProperty("xxx.xxx")
- 使用@ConfigurationProperties注解绑定配置信息到封装类中 ，例如： @ConfigurationProperties(prefix = "datasource")

# Springboot整合
## SpringBoot整合Junit
1. 注解名称：@SpringBootTest
2. 类型：测试类注解
3. 位置：测试类定义上方
4. 作用：设置JUnit加载的SpringBoot启动类
5. 范例：
```java
@SpringBootTest(classes = xxx.class)
```
6. 注意
   1. 如果测试类存在于引导类所在包或子包中无需指定引导类
   2. 测试类如果不存在于引导类所在的包或子包中需要通过classes属性指定引导类
