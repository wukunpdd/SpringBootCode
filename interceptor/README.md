# 拦截器
## 一. HandlerInterceptor 接口
HandlerInterceptor 接口是所有自定义拦截器或者 Spring Boot 提供的拦截器的鼻祖，所以，首先来了解下该接口。该接口中有三个方法，分别为 preHandle(……)、postHandle(……) 和 afterCompletion(……) 。

- preHandle(……) 方法：该方法的执行时机是，当某个 URL 已经匹配到对应的 Controller 中的某个方法，且在这个方法执行之前。所以 preHandle(……) 方法可以决定是否将请求放行，这是通过返回值来决定的，返回 true 则放行，返回 false 则不会向后执行。
- postHandle(……) 方法：该方法的执行时机是，当某个 URL 已经匹配到对应的 Controller 中的某个方法，且在执行完了该方法，但是在 DispatcherServlet 视图渲染之前。所以在这个方法中有个 ModelAndView 参数，可以在此做一些修改动作。
- afterCompletion(……) 方法：顾名思义，该方法是在整个请求处理完成后（包括视图渲染）执行，这时做一些资源的清理工作，这个方法只有在 preHandle(……) 被成功执行后并且返回 true 才会被执行。

## 二. 拦截器配置示例
```
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
```

## 三. 解决静态资源被拦截问题
除了在 MyInterceptorConfig 配置类中重写 addInterceptors 方法，还需要再重写一个方法 addResourceHandlers，用来将静态资源放开：
```
/**
 * 用来指定静态资源不被拦截，否则继承WebMvcConfigurationSupport这种方式会导致静态资源无法直接访问
 * @param registry
 */
@Override
protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    super.addResourceHandlers(registry);
}
```
但是，还有更方便的配置方式。我们不继承 WebMvcConfigurationSupport 类，直接实现 WebMvcConfigurer 接口，然后重写 addInterceptors 方法，将自定义的拦截器添加进去即可，如下：
```
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 实现WebMvcConfigurer不会导致静态资源被拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }
}
```

## 四. 取消拦截操作
我们可以定义一个注解，该注解专门用来取消拦截操作，如果某个 Controller 中的方法我们不需要拦截，即可在该方法上加上我们自定义的注解即可，下面先定义一个注解：
```
/**
 * 该注解用来指定某个方法不用拦截
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnInterception {
}
```
在preHandle()方法中还需增加如下判断
```
UnInterception unInterception = method.getAnnotation(UnInterception.class);
if (null != unInterception) {
    return true;
}
```
然后在不需要拦截的方法上增加@UnInterception注解即可。
