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