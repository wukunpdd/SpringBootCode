## 一. @ConfigurationProperties注解
需要的依赖：
```
compile('org.springframework.boot:spring-boot-configuration-processor')
```
示例代码：
```
@Component
@ConfigurationProperties(prefix = "url")
public class MicroServiceUrl {

    private String orderUrl;
    
    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }
}
```
与示例代码对应的配置文件内容
```
url:
  orderUrl: http://localhost:8002
```
MicroServiceUrl的使用：
```
@Resource
private MicroServiceUrl microServiceUrl;
```

## 二. @Value注解
使用示例：
```
@Value("${url.orderUrl}")
```
针对的配置文件是：
```
url:
  orderUrl: http://localhost:8002
```

## 三. 指定环境
指定环境代码：
```
spring:
  profiles:
    active:
    - dev
```
如果是dev环境，则除了使用application.yml配置文件外，也使用application-dev.yml配置文件

作用是针对不同的环境，使用不同的配置文件（如开发环境和生成环境要求的配置文件不相同）
