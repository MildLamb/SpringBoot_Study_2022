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

<hr>

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
- 字面值表达方式
```text
boolean: TRUE                                # TRUE,true,True,FALSE,false,False均可
float: 3.14                                  # 6.8523015e+5  # 支持科学计数法
int: 123                                     # 支持二进制，八进制，十六进制
null: ~                                      # 使用~表示null
string: HelloWorld                           # 字符串可以直接书写
string2: "HelloWorld"                        # 可以使用双引号包裹特殊字符
date: 2022-06-16                             # 日期默认使用 yyyy-MM-dd 格式
datetime: 2022-06-16T11:12:35+08:00          # 时间和日期之间使用T连接，最后使用+代表时区
```

## 读取Springboot配置中的数据
- 使用@Value配置SpEL读取单个数据
- 使用Environment类的实例对象获取属性，getProperty("xxx.xxx")
- 使用@ConfigurationProperties注解绑定配置信息到封装类中 ，例如： @ConfigurationProperties(prefix = "datasource")

<hr>

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

<hr>


## Springboot项目快速启动(Windows版)
1. 对SpringBoot项目打包(执行Maven构建指令package)
```text
mvn package
```
2. 运行项目(执行启动指令)
```text
java -jar springboot.jar
```
- 注意事项
jar支持命令行启动需要依赖maven插件支持，请确认打包时是否具有SpringBoot对应的maven插件
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
  </plugins>
</build>
```

<hr>

# Windows端口被占用
```bash
# 查询端口
netstat -ano
  协议    本地地址                 外部地址                状态          PID
  TCP    0.0.0.0:135            0.0.0.0:0              LISTENING       1828
  TCP    0.0.0.0:445            0.0.0.0:0              LISTENING       4
# 查询指定端口(类似模糊查询)
netstat -ano | findstr "端口号"
# 查询进程列表
# tasklist
C:\Users\MildLamb>tasklist
映像名称                       PID 会话名              会话#       内存使用
========================= ======== ================ =========== ============
System Idle Process              0 Services                   0          8 K
System                           4 Services                   0      3,536 K
# 查询指定进程号的进程
# tasklist | findstr "PID"
C:\Users\MildLamb>tasklist | findstr "5860"
svchost.exe                   5860 Services                   0     16,660 K
# 根据指定进程号结束进程
# taskkill -f -pid "PID"

# 根据指定进程名称结束进程
# taskkill -f -t -im "进程名称"
```

<hr>

## 启动jar包时使用临时属性
- 带临时属性启动SpringBoot
- 携带多个属性启动SpringBoot时，属性间使用空格分隔
```bash
java -jar xxx.jar --server.port=8080 --xxx.xxx.xxx=xxx
```

<hr>

## 配置文件优先级
1. SpringBoot中4级配置文件
  - 1级：classpath:application.yml    【优先级最低】               项目类路径下配置文件
  - 2级：classpath:config/application.yml                         项目类路径config目录中的配置文件
  - 3级：file:application.yml  (file就是和你jar包同级)             工程路径配置文件
  - 4级：file:config/application.yml  【优先级最高】               工程路径config目录中的配置文件

<hr>

## 多环境区分(yml版)
### 单配置文件多环境
1. 多环境开发需要设置若干常用环境，例如开发，生产，测试环境
2. yaml格式中设置多环境使用 --- 区分环境设置边界
3. 每种环境的区别在于加载的配置属性不同
4. 启用某种环境时需要指定启动时使用该环境

### 多配置文件格式
- 配置文件命名 application-xxx.yml
- 使用环境
```yml
# 指定使用的环境
spring:
  profiles:
    active: dev
```

### properties仅支持多文件格式，使用方式和yml类似

<hr>

## 环境组设置
- 根据功能对配置文件中的信息进行拆分，并制作成独立的配置文件，命名规则如下
  - application-devDB.yml
  - application-devRedis.yml
  - application-devMVC.yml
- 使用include属性在激活指定环境的情况下，同时对多个环境进行加载使其生效，多个环境间使用逗号分隔
```yml
# 公共属性配置，不区分环境的配置写这里
# 指定使用的环境
spring:
  profiles:
    # 使用分组的话，启动的时候会自动找到对应组环境
    active: prod
    group:
      "dev": devDB,devMVC
      "prod": prodDB,prodMVC
#    include: prodMVC
```
- 注意事项：环境中有相同属性时，最后加载的环境属性生效

<hr>

## Maven与SpringBoot多环境兼容
- maven的pom.xml中添加
```xml
<!-- 配置多环境 -->
<profiles>
    <profile>
        <id>env_dev</id>
        <properties>
            <!-- 属性名称自己定义，后面application中要使用 -->
            <enviroment.active>dev</enviroment.active>
        </properties>
        <!-- 默认激活 -->
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
    </profile>
    <profile>
        <id>env_prod</id>
        <properties>
            <profile.active>prod</profile.active>
        </properties>
    </profile>
</profiles>
```
- application.yml中读取maven配置,使用     @<properties>标签中指定的属性名称@
```yml
# 指定使用的环境
spring:
  profiles:
    # 读取maven中指定的环境名称
    active: @enviroment.active@

    group:
      "dev": devDB,devMVC
      "prod": prodDB,prodMVC
```
- 出现的问题：springboot报ScannerException：character ‘@‘ that cannot start any token. (Do not use @ for indentation
  - 如果你的springboot项目如果没有指定spring-boot-starter-parent的话，使用@@的时候就会报ScannerException异常：
  - 这时候，你需要在你pom文件的build节点加上如下的配置：
```xml
<build>     
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <!--开启过滤，用指定的参数替换directory下的文件中的参数-->
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```
- 基于SpringBoot读取Maven配置属性的前提下，如果在Idea下测试工程时，pom.xml每次更新需要手动compile方可生效

<hr>

## 日志基础操作
- 日志(log)作用
  - 编程期调试代码
  - 运营期记录信息

- 如何记录日志
```java
@RestController
public class EnvController {
    // 创建记录日志的对象
    private static final Logger log = LoggerFactory.getLogger(EnvController.class);

    @GetMapping("/env")
    public String getEnv(){

        // 级别由低到高
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");

        return "hello";
    }
}
```

- 日志级别
  - TRACE：运行堆栈信息，使用率低（不常用）
  - DEBUG：程序员调试代码使用
  - INFO：记录运维过程数据
  - WARN：记录运维过程报警数据
  - ERROR：记录错误堆栈信息
  - FATAL：灾难信息，合并计入ERROR（不常用）

- 设置日志级别
```yml
# 设置日志级别，输出调试信息，常用于检查系统运行状况
logging:
  level:
    root: warn
    # 设置某个包的日志级别
    com.mildlamb.controller: debug
    # 设置指定组的日志级别
    wildwolf: warn

  # 设置组的日志级别
  # 设置分组，对某个组设置日志级别
  group:
    wildwolf: com.mildlamb.controller,com.mildlamb.dao

# 开启debug模式，输出调试信息，常用于检查系统运行状况
debug: true
```

## 日志格式控制
```text
        时间              级别  PID           所属线程               所属类/接口名                                     日志信息
2022-06-14 17:08:49.140  INFO 15568 --- [           main] com.mildlamb.Springboot08LogApplication  : Starting Springboot08LogApplication using Java 1.8.0_221 on LAPTOP-AP9E7L32 with PID 15568 (C:\Code_Study\IDEA\SpringBoot_Study_2022\springboot-08-log\target\classes started by MildLamb in C:\Code_Study\IDEA\SpringBoot_Study_2022)
2022-06-14 17:08:49.142  INFO 15568 --- [           main] com.mildlamb.Springboot08LogApplication  : No active profile set, falling back to 1 default profile: "default"
2022-06-14 17:08:49.712  INFO 15568 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-06-14 17:08:49.718  INFO 15568 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-06-14 17:08:49.718  INFO 15568 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.63]
2022-06-14 17:08:49.800  INFO 15568 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-06-14 17:08:49.800  INFO 15568 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 630 ms
2022-06-14 17:08:49.994  INFO 15568 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-06-14 17:08:50.000  INFO 15568 --- [           main] com.mildlamb.Springboot08LogApplication  : Started Springboot08LogApplication in 1.199 seconds (JVM running for 1.694)
```
- 设置日志输出格式
```yaml
logging:
  # 设置日志的模板格式
  # %d - 时间，%m - 消息，注意要使用引号，%clr(%5p) clr表示使用彩色 , %5p - %p表示打印日志等级，5表示占用多少位置，%t - 所属线程，%n - 换行显式
  pattern:
#    console: "%d - %m %n"
    console: "%d %clr(%5p) --- [%16t] %clr(%-20c){cyan} : %m %n"
```

<hr>

## 启用热部署
### 手动启动热部署
- 依赖的坐标
```xml
<!-- 热部署工具 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```
- 手动激活热部署 手动 build project ， 快捷键 ctrl+F9
- 关于热部署：
  - 重启(Restart)：开发的代码，包含类，页面，配置文件等，加载位置restart类加载器
  - 重载(ReLoad)：jar包，加载位置base类加载器


### IDEA自动启动热部署
1. File --> setting --> build --> compiler --> 勾选 build project automatically
2. File --> Advanced Settings --> 勾选 Allow auto-make to start when ... 
3. 热部署延迟参数配置 ，快捷键 ctrl + shift + alt + / ， 选择 register

### 热部署范围配置
- 默认不触发热部署的目录列表
  - /META-INF/maven
  - /META-INF/resource
  - /resources
  - /static
  - /public
  - /template
- 自定义重启排除项
```yaml
spring:
  devtools:
    restart:
      # 设置不参与热部署的文件夹或文件
      exclude: static/**,public/**,config/application.yml
```

### 关闭热部署
- 配置文件级别设置关闭热部署
```yaml
spring:
  devtools:
    restart:
      # 在启动类添加系统属性，防止其他更高级别配置文件修改热部署开关
      enabled: false
```
- 系统属性级别关闭热部署，在启动类中(也适用于其他属性的高优先级配置)
```java
@SpringBootApplication
public class Springboot09HotApplication {

    public static void main(String[] args) {
        // 在系统级别设置关闭热部署
        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(Springboot09HotApplication.class, args);
    }
}
```

<hr>

## 第三方bean属性绑定
- 使用@ConfigurationProperties为第三方bean绑定属性
```java
@Bean
@ConfigurationProperties(prefix = "datasource")
public DruidDataSource dataSource(){
DruidDataSource ds = new DruidDataSource();
return ds;
}
```
- @EnableConfigurationProperties
  - @EnableConfigurationProperties注解可以将使用@ConfigurationProperties注解对应的类加入Spring容器中
```java
@SpringBootApplication
@EnableConfigurationProperties({ServerConfig.class})
public class Springboot10ConfigurationApplication {
}
```
```java
@Data
@ConfigurationProperties(prefix = "server-config")
public class ServerConfig {
    private String ipAddress;
    private int port;
    private long timeout;
}
```
- 注意事项：@EnableConfigurationProperties会将参数中的class实例化bean放入spring容器中

## 松散绑定
- @ConfigurationProperties绑定属性支持属性名松散绑定
```yaml
server-config:
  port: 8081
#   ip_address: 192.168.1.112   # unlined
  # ipAddress: 192.168.1.111    # 驼峰
  ip-address: 192.168.1.113     # 烤肉串模式
#  IPADDRESS: 192.168.101.1
#  IP_ADDRESS: 192.168.101.1    # 常量模式
```
- @Value不支持松散绑定
- 绑定前缀(prefix)的命名规范：仅能使用纯小写字母，数字，下划线作为合法的字符

## 常用计量单位
```java
@Data
@ConfigurationProperties(prefix = "server-config")
public class ServerConfig {
    // 时间类型 Duration
    // 指定时间单位
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration serverTimeOut;

    // 容量类型
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}
```
```yaml
server-config:
  server-timeout: 3
  data-size: 1024
```

## bean属性校验
1. 导入JSR303校验规范
```xml
<!-- 导入JSR303校验启动器 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
2. 开启校验功能，制定校验规则
```java
// 开启对当前bean的属性校验
@Validated
public class ServerConfig {
    // 制定校验规则
    @Max(value = 8888,message = "最大值不能超过8888")
    @Min(value = 0,message = "最小值不能低于0")
    private int port;
}
```

## 为测试环境添加属性
- 在启动测试环境时可以通过properties参数设置测试环境专用的属性
```java
// properties属性可以为当前测试用例添加临时的属性配置
//@SpringBootTest(properties = {"test.prop = kindred"})

// args属性也可以为当前的测试环境添加临时属性， 使用 -- ，但properties优先级更高
@SpringBootTest(properties = {"test.prop = Gnar"} , args = {"--test.prop = Kindred"})
class PropertiesAndArgsTest {

    @Value("${test.prop}")
    private String testVal;

    @Test
    void testProperties() {
        System.out.println(testVal);
    }

}
```
- 优势：比多环境开发中的测试环境影响的范围更小，仅对当前测试类有效



## 为测试环境添加临时bean
- 使用@Import注解导入bean到当前测试环境
```java
@SpringBootTest
@Import({MsgConfig.class})
public class ConfigurationTest {
    @Autowired
    private String msg;

    @Test
    void test(){
        System.out.println(msg);
    }
}
```

## 测试类使用web环境测试
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTest {
    @Test
    void testWeb(){

    }
}
```

## web环境模拟测试
- 虚拟请求测试
```java
// 开启虚拟MVC调用
@AutoConfigureMockMvc
public class WebTest {
    @Test
    void testWeb(){

    }

    @Test
    // 注入虚拟MVC调用对象
    void testMVC(@Autowired MockMvc mockMvc) throws Exception {
        // localhost:8080/role/info
        // 创建虚拟请求，当前访问 /role/info
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
        // 执行对应的虚拟请求
        mockMvc.perform(builder);
    }
}
```

### 虚拟请求状态匹配
```java
@Test
void testStatus(@Autowired MockMvc mvc) throws Exception{
    // 创建虚拟请求，当前访问 /role/info
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
    // 执行对应的虚拟请求
    ResultActions resultActions = mvc.perform(builder);

    // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
    // 定义本次调用的预期值
    StatusResultMatchers status = MockMvcResultMatchers.status();
    // 预计本次调用是成功的，状态200
    ResultMatcher statusOk = status.isOk();
    // 添加预计值到本次调用过程中进行匹配
    resultActions.andExpect(statusOk);
    }
```

### 虚拟响应体匹配
```java
@Test
void testRespBody(@Autowired MockMvc mvc) throws Exception{
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
    ResultActions resultActions = mvc.perform(builder);

    // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
    // 定义本次调用的预期值
    ContentResultMatchers content = MockMvcResultMatchers.content();
    // 预计本次虚拟调用返回的结果是
    ResultMatcher respInfo = content.string("这是珏宝的一些信息");
    // 添加预计值到本次调用过程中进行匹配
    resultActions.andExpect(respInfo);

}
```

### 虚拟响应体匹配(JSON)
```java
@Test
void testJson(@Autowired MockMvc mvc) throws Exception{
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/myrole");
    ResultActions resultActions = mvc.perform(builder);

    // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
    // 定义本次调用的预期值
    ContentResultMatchers content = MockMvcResultMatchers.content();
    // 预计响应的json结果
    String jsonResp = "{\"name\":\"Gnar\",\"age\":1500}";
    // 预计本次虚拟调用返回的结果是
    ResultMatcher respInfo = content.json(jsonResp);
    // 添加预计值到本次调用过程中进行匹配
    resultActions.andExpect(respInfo);

}
```

### 响应头类型匹配
```java
@Test
void testContentType(@Autowired MockMvc mvc) throws Exception{
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
    ResultActions resultActions = mvc.perform(builder);

    // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
    // 定义本次调用的预期值
    HeaderResultMatchers header = MockMvcResultMatchers.header();
    ResultMatcher contextType = header.string("Content-Type", "application/json");
    // 添加预计值到本次调用过程中进行匹配
    resultActions.andExpect(contextType);

}
```

<hr>

## 数据层测试事务回滚
- 为测试用例添加事务(@Transactional)，Springboot会对测试用例对应的事务提交操作进行回滚
```java
@SpringBootTest

// spring事务的注解
@Transactional  // 默认事务是回滚的
//@Rollback(value = false)  // 关闭回滚
public class DBTest {
    @Autowired
    private RoleController controller;

    @Test
    void testSave(){
        controller.saveRole("千珏",1500);
    }
}
```
- 如果想在测试用例中提交事务，可以通过@Rollback注解设置

## 随机测试数据设定
```yaml
testdata:
  role:
    id: ${random.int}          # 随机整数
    id2: ${random.int(100)}    # 100以内的正数
    type: ${random.int(1,9)}   # 1-9随机整数
    name: ${random.value}      # 随机字符串，MD5字符串，32位
    uuid: ${random.uuid}       # 随机uuid
    role-time: ${random.long}  # 随机整数(long范围)
```

<hr>

## springboot内置数据源配置
- Springboot提供了3种内嵌的数据源对象供开发者使用
  - HikariCP：默认内置数据源对象
  - Tomcat提供DataSource：HikariCP不可用的情况下，且在web环境中，将使用tomcat服务器配置的数据源对象
  - Commons DBCP：Hikari不可用，tomcat数据源也不可用，将使用dbcp数据源

## springboot内置的持久层配置
- JdbcTemplate
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

## springboot内嵌h2数据库
- 依赖坐标
```xml
<!-- H2数据库 -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```
- h2配置
```yaml
# h2数据库,内嵌数据库
# 配置h2数据库控制台
spring:
  h2:
    console:
      path: /h2
      enabled: true   # 上线的时候记得关闭

  # 第一次运行h2初始化数据
  datasource:
    url: jdbc:h2:~/test
    username: root
    password: 123456
    driver-class-name: org.h2.Driver
```

<hr>

# SpringBoot整合Redis
- 导入Springboot整合Redis的坐标
```xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
- 配置redis
```yaml
spring:
  redis:
    host: xxx.xxx.xxx.xxx
    port: 6379
    password: xxxxxxxxx
```
- 使用API
```java
@SpringBootTest
class Springboot13RedisApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void getRedis() {
        // 声明要操作那种数据类型
        ValueOperations ops = redisTemplate.opsForValue();
        Object age = ops.get("age");
        System.out.println(age);
    }

    @Test
    void setRedis(){
        // 声明要操作那种数据类型
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("age",1500);
    }
}
```

<hr>

# Springboot整合Mongodb
- MongoDb是一个开源，高性能，无模式的文档型数据库。NoSQL数据库产品中的一种，是最像关系型数据库的非关系型数据库
- 整合后的使用方法与Redis类似

# Springboot整合ES
## ElasticSearch(ES)
- 创建索引，添加条件  PUT
```json
{
    "mappings":{
        "properties":{
            "id":{
                "type":"keyword",
                "copy_to":"all"
            },
            "name":{
                "type":"text",
                "analyzer":"ik_max_word",
                "copy_to":"all"
            },
            "age":{
                "type":"integer"
            },
            "all":{
                "type":"text",
                "analyzer":"ik_max_word"
            }
        }
    }
}
```
- 创建文档(数据)
```text
POST        http://localhost:9200/roles/_doc          # 使用系统生成id
POST        http://localhost:9200/roles/_create/1     # 使用指定id
POST        http://localhost:9200/roles/_doc/1        # 使用指定id，不存在则创建，存在则更新(会导致版本号更新)
请求体携带json数据
```
- 查询文档
```text
GET         http://localhost:9200/roles/_doc/1        # 查询单个文档
GET         http://localhost:9200/roles/_search       # 查询所有文档
```
- 条件查询
```text
GET         http://localhost:9200/roles/_search?q=name:xxx
```
- 删除文档
```text
DELECT      http://localhost:9200/books/_doc/1
```
- 修改文档(全量修改)
```text
PUT         http://localhost:9200/roles/_doc/1
请求体携带json数据
```
- 文档修改(部分修改)
```text
POST        http://localhost:9200/roles/_update/1
{
    "doc":{
        "paramName":"newValue"
    }
}
```
## 整合ES
- 导入坐标
```xml
<!-- 高级别ES客户端 -->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
</dependency>
```
- 测试使用
```java
@SpringBootTest
class Springboot15EsApplicationTests {

    // 显式已弃用
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @BeforeEach
    void beforeTest(){
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    @AfterEach
    void AfterTest() throws IOException {
        restHighLevelClient.close();
    }

    @Test
    void testCreateIndex() throws IOException {

        // 创建一个名为roles的索引
        CreateIndexRequest request = new CreateIndexRequest("roles");
        // ES客户端执行创建索引的请求
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

    }
```

- 创建带参数的索引
```java
    @Test
    void testCreateIndexByIK() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("roles");
        // 设置请求的参数
        String json = "{\n" +
                "    \"mappings\":{\n" +
                "        \"properties\":{\n" +
                "            \"id\":{\n" +
                "                \"type\":\"keyword\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"name\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"age\":{\n" +
                "                \"type\":\"integer\"\n" +
                "            },\n" +
                "            \"all\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        request.source(json, XContentType.JSON);
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

```
- 添加文档(单个和多个)
```java
    @Test
    void testCreateDoc() throws IOException {

        /*
            一次插入一条数据
         */
/*        Role role = roleDao.selectById(1);

        IndexRequest request = new IndexRequest("roles").id(role.getId().toString());
        String json = JSON.toJSONString(role);
        request.source(json,XContentType.JSON);
        restHighLevelClient.index(request,RequestOptions.DEFAULT);*/

        /*
            批处理插入数据
         */
        List<Role> roles = roleDao.selectList(null);

        // 创建批处理请求 容器
        BulkRequest bulk = new BulkRequest();

        // 通过循环 将请求添加到容器中，准备一次执行
        for (Role role : roles) {
            IndexRequest request = new IndexRequest("roles").id(role.getId().toString());
            String json = JSON.toJSONString(role);
            request.source(json,XContentType.JSON);
            bulk.add(request);
        }
        restHighLevelClient.bulk(bulk,RequestOptions.DEFAULT);

    }
```
- 查询文档
```java
    @Test
    // 按文档id查询
    void testGetByDocId() throws IOException {
        GetRequest getRrequest = new GetRequest("roles","1");
        GetResponse documentFields = restHighLevelClient.get(getRrequest, RequestOptions.DEFAULT);
        String result = documentFields.getSourceAsString();
        System.out.println(result);

    }


    @Test
    // 按条件查询
    void testGetByQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("roles");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("name","kindred"));
        searchRequest.source(builder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            String result = hit.getSourceAsString();
            Role role = JSON.parseObject(result,Role.class);
            System.out.println(role);
        }
    }
```

<hr>

# 缓存
- 缓存是一种介于数据永久存储介质与数据应用之间的数据临时存储介质
- 使用缓存可以有效的减少低速数据读取过程的次数(例如磁盘IO)，提高系统新能
- 缓存不仅可以用于提高永久性存储介质的数据读取效率，还可以提供临时的数据存储空间

## Springboot的缓存
- 导入springboot的缓存依赖
```xml
<!-- springboot缓存依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```
- 启用缓存
```java
@SpringBootApplication
// 开启缓存
@EnableCaching
public class Springboot16Cache {
    public static void main(String[] args) {
        SpringApplication.run(Springboot16Cache.class,args);
    }
}
```
- 使用缓存
```java
// value指定缓存名称  key指定缓存中的键  #paramName可以取到对应参数
@Cacheable(value = "cacheSpace",key = "#rid")
public Role getRoleById(Integer rid) {
    return roleDao.selectById(rid);
} 
```

## 变更缓存供应商 使用ehcache
- 添加Ehcache坐标
```xml
<!-- Ehcache -->
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>2.10.4</version>
</dependency>
```
- 配置文件中设置使用Ehcache
```yml
spring:
  # 指定使用 ehcacahe
  cache:
    type: ehcache
```
- 编写Ehcache配置文件(ehcache.xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
    <!--
       diskStore：为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。参数解释如下：
       user.home – 用户主目录
       user.dir  – 用户当前工作目录
       java.io.tmpdir – 默认临时文件路径
     -->
    <diskStore path="C:\ehcache"/>
    <!--
       defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。
     -->
    <!--
      name:缓存名称。
      maxElementsInMemory:缓存最大数目
      maxElementsOnDisk：硬盘最大缓存个数。
      eternal:对象是否永久有效，一但设置了，timeout将不起作用。
      overflowToDisk:是否保存到磁盘，当系统当机时
      timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
      timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
      diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
      diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
      diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
      memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
      clearOnFlush：内存数量最大时是否清除。
      memoryStoreEvictionPolicy:可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
      FIFO，first in first out，这个是大家最熟的，先进先出。
      LFU， Less Frequently Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
      LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
   -->
    <defaultCache
            eternal="false"
            maxElementsInMemory="10000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="60"
            timeToLiveSeconds="60"
            memoryStoreEvictionPolicy="LRU"/>

    <!--  name要和@Cacheable的value属性对应，就是哪一部分缓存存入哪个缓存空间   -->
    <cache
            name="smsCheckCode"
            eternal="false"
            maxElementsInMemory="5000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="10"
            timeToLiveSeconds="10"
            memoryStoreEvictionPolicy="LRU"/>

</ehcache>
```

# memcached下载安装
- 下载百度，去菜鸟下载
- 安装memcached
  - 使用管理员身份运行cmd指令,进入memcached目录
  - 输入  memcached.exe -d install
  - 启动/停止  memcached.exe -d start/stop

## springboot整合memcached
- 导入memcached依赖
```xml
<!-- memcached -->
<dependency>
    <groupId>com.googlecode.xmemcached</groupId>
    <artifactId>xmemcached</artifactId>
    <version>2.4.7</version>
</dependency>
```
- 配置memcached服务器的一些属性(名称自定义，使用读取配置的方式进行)
```yaml
# 自定义memcached属性
memcached:
  # memcached服务器地址
  servers: localhost:11211
  # 连接池数量
  poolSize: 10
  # 设置超时时间
  opTimeout: 3000
```
- 创建读取配置属性的信息类，加载上面的配置
```java
@Component
@Data
@ConfigurationProperties(prefix = "memcached")
public class XMemcachedProperties {
    private String servers;
    private int poolSize;
    private long opTimeout;
}
```
- 创建memcached客户端
```java
@Configuration
public class XMemcached {

    @Autowired
    private XMemcachedProperties properties;

    @Bean
    public MemcachedClient getMemcachedClient() throws IOException {
        MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(properties.getServers());
        memcachedClientBuilder.setOpTimeout(properties.getOpTimeout());
        MemcachedClient memcachedClient = memcachedClientBuilder.build();
        return memcachedClient;
    }
}
```
- 使用memcached缓存
```java
    @Autowired
    private MemcachedClient memcachedClient;

    @Override
    public String sendCodeToSMS(String tele) {
        String generator = CodeUtil.generator(tele);
        // 参数分别为   键 过期时间(0表示永不过期) 值
        try {
            memcachedClient.set(tele,10,generator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generator;
    }

    @Override
    public boolean checkCode(SMSCode smsCode){
        String CacheCode = null;
        try {
            CacheCode = memcachedClient.get(smsCode.getTele()).toString();
            System.out.println("=================== " + CacheCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return smsCode.getCode().equals(CacheCode);
    }
```

# jetCache
## jetcache远程缓存方案
- 导入依赖
```xml
<!-- jetcacahe -->
<dependency>
    <groupId>com.alicp.jetcache</groupId>
    <artifactId>jetcache-starter-redis</artifactId>
    <version>2.6.4</version>
</dependency>
```
- 启动类开启缓存注解
```java
@SpringBootApplication
// jetcacahe启用缓存的主开关
@EnableCreateCacheAnnotation
public class Springboot17JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot17JetcacheApplication.class, args);
    }
}
```
- 编写jetcache配置，使用远程redis
```yaml
# jetcacahe配置
jetcache:
  # 本地
  local:
  default:
    type: linkedhashmap
    # 必配项 key转换器
    keyConvertor: fastjson
  
  # 远程
  remote:
    default:
      type: redis
      host: xxx.xxx.xxx.xxx
      port: 6379
      password: myredispwd
      poolConfig:
        maxTotal: 50

    sms:
      type: redis
      host: xxx.xxx.xxx.xxx
      port: 6379
      password: myredispwd
      poolConfig:
        maxTotal: 50
```
- 创建缓存空间并使用
```java
@Service
public class SMSCodeServiceImpl implements SMSCodeService {

  // 创建缓存空间（远程）
//    @CreateCache(area = "sms" , name = "smsCache",expire = 360,timeUnit = TimeUnit.SECONDS,cacheType = CacheType.REMOTE)
//    private Cache<String,String> jetCache;

    // 创建缓存空间（本地）
    @CreateCache(name = "smsCache",expire = 360,timeUnit = TimeUnit.SECONDS,cacheType = CacheType.LOCAL)
    private Cache<String,String> jetCache;
  
    @Override
    public String sendCodeToSMS(String tele) {
        // 生成验证码
        String code = CodeUtil.generator(tele);
        jetCache.put(tele,code);
        return code;
    }

    @Override
    public boolean checkCode(SMSCode smsCode){
        String CacheCode = jetCache.get(smsCode.getTele());
        return smsCode.getCode().equals(CacheCode);
    }
}
```

## jetcache启用方法注解
- 启动类开启方法注解缓存开关
```java
@SpringBootApplication
// jetcacahe启用缓存的主开关
@EnableCreateCacheAnnotation
// 开启方法缓存注解的开关
@EnableMethodCache(basePackages = {"com.mildlamb"})
public class Springboot17JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot17JetcacheApplication.class, args);
    }

}
```
- 使用方法缓存注解
```java
    // 启用方法缓存
    @Cached(name = "roles_",key = "#rid",expire = 360,timeUnit = TimeUnit.SECONDS,cacheType = CacheType.REMOTE)
    // 刷新缓存 重新查询重新缓存  时间要考虑清楚
    @CacheRefresh(refresh = 10,timeUnit = TimeUnit.SECONDS)
    public Role getRoleById(Integer rid) {
        return roleDao.selectById(rid);
    }
    
    // 执行更新操作时，更新缓存
    // 缓存哪个缓存空间   键   值
    @CacheUpdate(name = "roles_",key = "#role.id",value = "#role")
    public boolean updateRole(Role role) {
        return roleDao.updateById(role) > 0;
    }
    
    // 执行删除操作时，删除缓存
    @CacheInvalidate(name = "roles_",key = "#id")
    public boolean deleteRole(Integer id) {
        return roleDao.deleteById(id) > 0;
    }
```
- 方法缓存要求：缓存的对象必须保证是序列化的
```java
public class Role implements Serializable {
}
```
- 配置中声明，键转换器，转码后类型和解码后类型
```yaml
# jetcacahe配置
jetcache:
  # 每过一分钟向控制台输出一次统计数据
  statIntervalMinutes: 1

  remote:
    default:
      type: redis
      host: XXX.XXX.XXX.XXX
      port: 6379
      password: myredispwd
      # 指定键转换器，转码后类型和解码后类型
      keyConvertor: fastjson
      valueEncode: java
      valueDecode: java
```

# j2cache实现缓存
- 导入依赖(由于Springboot已经导入了slf4j，因此j2cache-core中要排除slf4j)
```xml
<!-- 下面两个是j2cache所需依赖 -->

<dependency>
    <groupId>net.oschina.j2cache</groupId>
    <artifactId>j2cache-spring-boot2-starter</artifactId>
    <version>2.8.0-release</version>
</dependency>

<dependency>
    <groupId>net.oschina.j2cache</groupId>
    <artifactId>j2cache-core</artifactId>
    <version>2.8.5-release</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </exclusion>
    </exclusions>

</dependency>

<!-- ehcache -->
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>2.10.4</version>
</dependency>
```
- 配置j2cache
```yaml
# j2cache配置
j2cache:
  config-location: j2cache.properties
```
- 编写ehcache(ehcache.xml)配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">

    <diskStore path="C:\ehcache"/>

    <defaultCache
            eternal="false"
            maxElementsInMemory="10000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="60"
            timeToLiveSeconds="60"
            memoryStoreEvictionPolicy="LRU"/>

</ehcache>
```
- 编写j2cache(j2cache.properties)配置文件
```properties
# 1级缓存
j2cache.L1.provider_class = ehcache
ehcache.configXml = ehcache.xml

# 2级缓存
j2cache.L2.provider_class = net.oschina.j2cache.cache.support.redis.SpringRedisProvider
j2cache.L2.config_section = redisConf
redisConf.hosts = 150.158.46.233:6379
redisConf.password = mildlambW2kindredwildwolfW2snowgnar

# 1级缓存中的数据如何到达2级缓存
j2cache.broadcast = net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy
```
- 使用j2cache
```java
@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    // 创建j2cache缓存对象
    @Autowired
    private CacheChannel cacheChannel;

    @Override
    public String sendCodeToSMS(String tele) {
        // 生成验证码
        String code = CodeUtil.generator(tele);
        // 缓存空间名  键  值
        cacheChannel.set("sms",tele,code);
        return code;
    }

    @Override
    public boolean checkCode(SMSCode smsCode){
        String CacheCode = cacheChannel.get("sms",smsCode.getTele()).asString();
        return smsCode.getCode().equals(CacheCode);
    }
}
```

<hr>

# 定时任务
## Springboot整合Quartz
- 相关概念
  - 工作(job)：用于定义具体执行的工作
  - 工作明细(JobDetail)：用于描述定时工作相关的信息
  - 触发器(Trigger)：用于描述触发工作的规则，常用cron表达式定义规则
  - 调度器(Scheduler)：描述了工作明细与触发器的对应关系

- 导入Springboot整合Quartz的坐标
```xml
<!-- Quartz -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```
- 定义具体要执行的任务，继承QuartzJobBean
```java
public class MyQuartz extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("千珏绝绝子...");
    }
}
```
- 定义工作明细与触发器，并绑定对应关系
```java
@Configuration
public class QuartzConfig {
    // 工作明细
    @Bean
    public JobDetail jobDetail(){
        // 绑定具体的工作
        return JobBuilder.newJob(MyQuartz.class).storeDurably().build();
    }

    // 触发器
    @Bean
    public Trigger jobTrigger(){
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        // 绑定对应的工作明细
        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(scheduleBuilder).build();
    }
}
```

## Springboot Task
- 启动类开启定时任务开关 @EnableScheduling
```java
@SpringBootApplication
// 开启定时任务功能
@EnableScheduling
public class Springboot19TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot19TaskApplication.class, args);
    }

}
```
- 定时任务上添加 @Scheduled
```java
@Component
public class MyTask {
    @Scheduled(cron = "0/5 * * * * ?")
    public void doTask(){
        System.out.println(Thread.currentThread().getName() + " : 千青灵花王玉瓷间");
    }
}
```

<hr>

# 发送简单邮件
- 导入发送邮件依赖
```xml
<!-- JavaMail -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```
- 编写配置
```yaml
spring:
  mail:
    username: **********@qq.com
    password: ************
    host: smtp.qq.com
```
- 编写邮件设置邮件任务
```java
@Service
public class SendMailServiceImpl implements SendMailService {

    @Resource
    private JavaMailSender mailSender;

    // 发送人
    private String mailFrom = "***@qq.com";
    // 接收人
    private String mailTo = "***@qq.com";
    // 标题
    private String mailTitle = "千青灵花王玉瓷间";
    // 正文
//    private String mailText = "如果天空不亮，那就摸黑生活;如果发出声音是危险的，那就保持沉默；如果" +
//            "自觉无力发光，那就不必去照亮他人。但是，不要习惯了黑暗就为黑暗辩护，不要为了自己的苟且洋洋得意，" +
//            "不要嘲讽那些比自己更勇敢，更有热量的人们。我们可以卑微如尘土，但不可以扭曲如蛆虫。";

    private String mailText = "拥抱生命便意味着接受死亡";

    @Override
    public void sendMail() {
        // 创建邮件消息容器
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件消息
        message.setFrom(mailFrom+"(MildLamb)");
        message.setTo(mailTo);
        message.setSubject(mailTitle);
        message.setText(mailText);

        mailSender.send(message);
    }
}
```
- 带附件和html结构解析的邮件
```java
    @Override
    public void sendMail(){
        try {
            // 创建邮件消息容器(高端版)
            MimeMessage message = mailSender.createMimeMessage();
            // 设置邮件消息(第二个参数表示允许多部件，附件也是部件的一种)
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setFrom(mailFrom+"(温柔小羊)");
            messageHelper.setTo(mailTo);
            messageHelper.setSubject(mailTitle);
            // 第二个参数表示是否支持html解析
            messageHelper.setText(mailText,true);

            // 添加附件
            File file = new File("C:\\Users\\MildLamb\\Pictures\\Kindred_83776140.png");
            messageHelper.addAttachment("小羔羊.png",file);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

<hr>

# 消息
- JMS(Java Message Service)：一个规范，等同于JDBC规范，提供了与消息服务相关的API接口
  - JMS消息模型
    - peer-2-peer：点对点模型，消息发送到一个队列中，队列保存消息。队列的消息只能被一个消费者消费，或超时
    - publish-subscribe：发布订阅模型，消息可以被多个消费者消费，生产者和消费者完全独立，不要要感知对方存在
  - JMS消息种类：
    - TextMessage
    - MapMessage
    - BytesMessage(最常用)
    - StreamMessage
    - ObjectMessage
- AMQP(advanced message queuing protocol)：一种协议(高级消息队列协议，也是消息代理规范)，规范了JMS
  - 优点：具有跨平台，服务器提供商，生产者，消费者可以使用不同的语言来实现
  - AMQP消息模型
    - direct exchange
    - fanout exchange
    - topic exchange
    - headers exchange
    - system exchange
  - AMQP消息种类：byte[]

## Springboot整合ActiveMQ
- 导入依赖
```xml
<!-- activemq -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```
- 进行基础配置
```yaml
spring:
  activemq:
    broker-url: tcp://localhost:61616
  # 设置消息存放的位置
  jms:
    template:
      default-destination: mildlamb

    # 开启发布订阅模式
    pub-sub-domain: true
```
- 创建ActiveMQ配置类
```java
@Configuration
public class ActiveMqConfig {
    @Bean
    ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        jmsTemplate.setPriority(999);
        return jmsTemplate;
    }

    @Bean(value = "jmsMessagingTemplate")
    JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
        JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
        // 指定消息存放的队列名称，也可以在发送消息方法时传参的时候指定
//        messagingTemplate.setDefaultDestinationName("mildlamb");
        return messagingTemplate;

    }
}
```
- 生产者生产消息发送给消息队列
```java
@Service
public class MessageServiceActivemqImpl implements MessageService {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短信的订单已纳入处理队列，id：" + id);
        // 向mq发送消息
        messagingTemplate.convertAndSend("order.queue.id",id);
    }

    @Override
    public String doMessage() {
        String removeId = messagingTemplate.receiveAndConvert("order.queue.id",String.class);
        System.out.println("已完成短信发送业务，id：" + removeId);
        return removeId;
    }
}
```
- 监听者监听消息队列，进行消息消费
```java
@Component
public class MessageListener {
    // 监听消息队列中是否有消息，有消息就进行处理

    // 指定监听哪个消息队列
    @JmsListener(destination = "order.queue.id")

    // 将这次消息处理后的返回值发送到新的队列中
    @SendTo("order.other.queue.id")
    public String receive(String doneId){
        System.out.println("已监听到消息队列中的消息，下面开始处理，已完成短信发送业务，id：" + doneId);
        return "new:" + doneId;
    }
}
```

## Springboot整合RabbitMQ
- 导入依赖
```xml
<!-- rabbitmq -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
- 配置RabbitMQ
```yaml
spring:
  # rabbitmq配置
  rabbitmq:
    host: localhost
    port: 5672
```
- 配置类中创建消息队列，交换机，以及交换机和消息队列的绑定
```java
@Configuration
public class RabbitmqConfig {
    // 创建存放消息的消息队列
    @Bean
    public Queue directQueue(){
        // durable：是否持久化，默认true
        // exclusive：是否当前连接专用，默认false，即连接关闭后队列即被删除
        // autoDelete：是否自动删除，当生产者和消费者不再使用此队列时，自动删除
        return new Queue("direct_queue",true,false,false);
    }

    // 创建交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    // 完成交换机和消息队列间的绑定
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
    }
}
```
- 生产消息，将消息给交换机
```java
@Service
public class MessageServiceRabbitmqDirectImpl implements MessageService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短信的订单已纳入处理队列(rabbitmq direct)，id：" + id);
        // 交换机名称     routing key     发送的消息
        amqpTemplate.convertAndSend("directExchange","direct",id);
    }
}
```
- 消费者监听消息队列中的消息
```java
@Component
public class MessageListener {
    @RabbitListener(queues = {"direct_queue"})
    public void receive(String doneId){
        System.out.println("RabbitMQ已监听到消息队列中的消息，下面开始处理，已完成短信发送业务，id：" + doneId);
    }
}
```

- 另一种消息分发模式(Topic模式)
```java
@Configuration
public class RabbitmqConfig_Topic {
    // 创建存放消息的消息队列
    @Bean
    public Queue topicQueue(){
        return new Queue("topic_queue");
    }
    
    // 创建交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    // 完成交换机和消息队列间的绑定
    /*
        *号：用来匹配一个单词，且单词是必须出现的，1个或多个
        #号：用来表示任意数，0个或多个
     */
    @Bean
    public Binding bindingtopic(){
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.*.id");
    }
}
```

<hr>

# 程序监控
- 创建监视服务端程序
- 导入依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>your springboot version</version>
</dependency>
```
- 配置监控服务端
```yaml
server:
  port: 8080
```
- 开启监控服务端开关
```java
@SpringBootApplication
@EnableAdminServer
public class Springboot22AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot22AdminServerApplication.class, args);
    }

}
```

## actuator
- Actuator提供了Springboot生产就绪功能，通过端点的配置与访问，获取端点信息
- 端点描述了一组监控信息，Springboot提供了多个内置端点，也可以根据需要自定义端点信息
- 访问当前应用的所有端点信息: /actuator
- 访问端点的详细信息: /actuator/端点名称

### actuator添加自定义info内容
- 创建一个类实现 InfoContributor接口
```java
@Component
public class InfoConfig implements InfoContributor {

    @Value("${my-appInfo.artifact}")
    private String artifact;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("runTime",System.currentTimeMillis());
        Map infoMap = new HashMap();
        infoMap.put("author","MildLamb");
        infoMap.put("appName",artifact);
        infoMap.put("特别鸣谢","温柔小羊");
        builder.withDetails(infoMap);
    }
}
```
### 添加自定义health内容
- 创建一个类继承 AbstractHealthIndicator
```java
@Component
public class HealthConfig extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Map infoMap = new HashMap();
        infoMap.put("author","MildLamb");
        infoMap.put("特别鸣谢","温柔小羊");
        builder.withDetails(infoMap);
        builder.status(Status.UP);
    }
}
```
### 自定义端点
- 创建一个类，声明为端点
```java
@Component
// 声明端点，指明id，是否默认开启
@Endpoint(id="pay",enableByDefault = true)
public class PayPoint {
    // 设置端点被访问时，自动调用方法
    @ReadOperation
    public Object getPay(){
        System.out.println("======================");
        System.out.println("========= pay ========");
        System.out.println("======================");
        Map payMap = new HashMap();
        payMap.put("level 5",300);
        return payMap;
    }
}
```