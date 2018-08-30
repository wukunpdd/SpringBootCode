# 核心知识点

## 一. jackson
Spring Boot默认使用的是jackson
jackson对null的处理配置代码JacksonConfig

## 二. fastjson
如果想使用fastjson替代默认的jackson，仅需增加 compile("com.alibaba:fastjson:1.2.35") 配置代码即可
fastjson对null的处理配置代码FastJsonConfig

## 三. 数据封装
对返回的json数据进行封装的类JsonResult

## 四. @RestController注解的源代码
```
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface RestController {
    String value() default "";
}
```
