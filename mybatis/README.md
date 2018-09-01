## 一. MyBatis依赖
```
compile('org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.2')
```

## 二. MyBatis的配置
```
mybatis:
  # 指定别名设置的包为所有entity
  type-aliases-package: cn.wukun.domain
  configuration:
    map-underscore-to-camel-case: true # 驼峰命名规范
  mapper-locations: # mapper映射文件位置
    - classpath:mapper/*.xml
```

## 三. 基于xml的使用
UserScoreMapper.xml代码
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.wukun.dao.UserScoreMapper">
    <resultMap id="BaseResultMap" type="cn.wukun.domain.UserScore">

        <id column="user_id" jdbcType="INTEGER" property="userId" />
        <result column="user_name" jdbcType="VARCHAR" property="userName" />
        <result column="user_mobile" jdbcType="VARCHAR" property="userMobile" />
        <result column="score_value" jdbcType="INTEGER" property="scoreValue" />
        <result column="create_time" jdbcType="TIMESTAMP" property="create_time" />
    </resultMap>

    <select id="getUserScoreByUserName" resultType="UserScore" parameterType="String">
        select * from user_score where user_name = #{userName}
    </select>
</mapper>
```
对应的Mapperd中只需下面的声明即可
```
UserScore getUserScoreByUserName(String userName);
```

## 四. 基于注解的使用
基于注解的整合就不需要 XML 配置文件了，MyBatis 主要提供了 @Select， @Insert， @Update， Delete 四个注解。这四个注解用的比较多，也很简单，注解后面跟上对应的 SQL 语句即可，我们举个例子：
```
@Select("select * from user_score where user_id = #{userId}")
UserScore getUserScoreByUserId(@Param("userId") Integer userId);
```

## 五. Mapper的启用
1. 在对应Mapper接口上面使用@Mapper注解
2. 在启动类上加上@MapperScan注解，并配置接口的所在包地址

## 六. 事务常见错误
### 6.1 异常并没有被“捕获”到
Spring Boot 默认的事务规则是遇到运行异常（RuntimeException）和程序错误（Error）才会回滚。比如抛出的 RuntimeException，可完成回滚，而抛出 SQLException，则无法回滚。针对非检测异常，如果要进行事务回滚，可以在 @Transactional 注解中使用 rollbackFor 属性指定异常，比如 @Transactional(rollbackFor = Exception.class)，这样就没有问题了。

### 6.2 异常被“吃”掉
针对这种情况只需要保证不在事务中使用try catch即可

### 6.3 事务范围
