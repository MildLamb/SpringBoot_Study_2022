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