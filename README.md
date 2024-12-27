## take-out-demo

出于扩展技术栈的目的学习了 take out 开源项目，这里是学习记录。

## 项目结构

### 后端部分

技术栈：Spring Boot3 + Mybatis-Plus + MySQL + Redis + RabbitMQ

- take-out-common：存放通用的工具类、全局常量、公共配置等
- take-out-pojo：包括dao类、dto类、vo类
- take-out-service：核心业务逻辑


- 使用版本更高的 Spring Boot3 框架与 JDK17
- 使用 Mybatis-Plus 减少手动编写 SQL 语句的工作量
- 使用 OpenAPI3 与 knife4j 生成API文档
- 调用 MyBetaObjectHandler 接口实现公共字段自动填充，省去手动编写切面
- 缓存方面使用 Redis ，在服务层使用缓存注解，在购物车服务中使用哈希结构缓存
- 全局异常处理方面，完善自定义异常类与返回结果
- 使用消息队列 RabbitMQ 与 WebSocket 实现实时消息推送

### 前端部分

技术栈：Vue3 + TypeScript + Vue-Router + Pinia + Axios

- take-out-frontend

