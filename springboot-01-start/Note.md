## Parent
1. 开发SpringBoot程序要继承spring-boot-starter-parent
2. spring-boot-starter-parent中的spring=boot-dependencies定义了若干个依赖管理
3. 继承parent模块可以避免多个依赖同时使用时出现依赖版本冲突
4. 继承parent的形式也可以采用引入依赖的形式实现