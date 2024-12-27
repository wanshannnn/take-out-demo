## take-out-demo

学习记录，是对sky-take-out的复刻，在实现其原有功能的基础上进行了部分改动，主要目的是扩展技术栈。

#### 项目结构

- take-out-common：存放通用的工具类、全局常量、公共配置等。
- take-out-pojo：包括dao类、dto类、vo类，数据库，定义数据库实体与数据传输对象。
- take-out-service：包括服务接口、服务实现类以及其他的核心业务逻辑。
- take-out-frontend：前端部分，包括登陆、商品管理等页面。

#### 项目内容

- 使用版本更高的Spring Boot3框架，使用Mybatis-Plus减少手动编写SQL语句的工作量
- 使用OpenAPI3与knife4j生成API文档
- 调用MyBetaObjectHandler接口实现公共字段自动填充，省去手动编写切面
- 缓存方面使用Redis，在服务层使用缓存注解，在购物车服务中使用哈希结构缓存
- 全局异常处理方面，完善自定义异常类与返回结果
- 使用消息队列RabbitMQ与WebSocket实现实时消息推送