## take-out-demo

基于对sky-take-out，在实现原有功能的基础上进行了部分优化，是一个以学习为导向的项目。

#### 项目结构与内容

- take-out-common：存放通用的工具类、全局常量、公共配置等。
- take-out-pojo：包括dao类、dto类、vo类，数据库，定义数据库实体与数据传输对象。
- take-out-service：包括服务接口、服务实现类以及其他的核心业务逻辑。

#### 优化内容

- 更新版本：Spring Boot2 -> Spring Boot3；Swagger -> OpenAPI3
- 数据库：Mybatis -> Mybatis-Plus，减少SQL语句编写
- 公共字段自动填充：手动实现切面 -> 调用MyBetaObjectHandler接口
- 缓存：在服务层使用缓存注解；购物车使用哈希结构缓存
- 全局异常处理：完善自定义异常类与返回结果
- 实时消息推送：@Scheduled注解 + WebSocket -> RabbitMQ + WebSocket