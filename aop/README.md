## 切面AOP处理
需要的依赖：
```
compile('org.springframework.boot:spring-boot-starter-aop')
```

@Aspect 注解用来描述一个切面类，定义切面类的时候需要打上这个注解。@Component 注解将该类交给 Spring 来管理。
```
@Aspect
@Component
public class LogAspectHandler {

}
```

核心注解：

- @Pointcut：定义一个切面，即上面所描述的关注的某件事入口；
- @Before：在做某件事之前做的事；
- @After：在做某件事之后做的事；
- @AfterReturning：在做某件事之后，对其返回值做增强处理；
- @AfterThrowing：在做某件事抛出异常时，处理。

一个以来配置类示例：
```
@Aspect
@Component
public class LogAspectHandler {

    private final Logger logger = LoggerFactory.getLogger(LogAspectHandler.class);

    /**
     * 定义一个切面，拦截com.itcodai.course09.controller包和子包下的所有方法
     */
    @Pointcut("execution(* cn.wukun.web..*.*(..))")
    public void pointCut() {}

    /**
     * 在上面定义的切面方法之前执行该方法
     * @param joinPoint jointPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("====doBefore方法进入了====");

        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        logger.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的url和ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求ip
        String ip = request.getRemoteAddr();
        logger.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }

    /**
     * 在上面定义的切面方法之后执行该方法
     * @param joinPoint jointPoint
     */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {

        logger.info("====doAfter方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("方法{}已经执行完", method);
    }

    /**
     * 在上面定义的切面方法返回后执行该方法，可以捕获返回对象或者对返回对象进行增强
     * @param joinPoint joinPoint
     * @param result result
     */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        logger.info("方法{}执行完毕，返回参数为：{}", classMethod, result);
        // 实际项目中可以根据业务做具体的返回值增强
        logger.info("对返回参数进行业务上的增强：{}", result + "增强版");
    }

    /**
     * 在上面定义的切面方法执行抛异常时，执行该方法
     * @param joinPoint jointPoint
     * @param ex ex
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        // 处理异常的逻辑
        logger.info("执行方法{}出错，异常为：{}", method, ex);
    }
}
```

在 @AfterReturning 注解 中，属性 returning 的值必须要和参数保持一致，否则会检测不到。

@AfterThrowing 注解，当被切方法执行过程中抛出异常时，会进入 @AfterThrowing 注解的方法中执行，在该方法中可以做一些异常的处理逻辑。要注意的是 throwing 属性的值必须要和参数一致，否则会报错。该方法中的第二个入参即为抛出的异常。

JointPoint 对象很有用，可以用它来获取一个签名，利用签名可以获取请求的包名、方法名，包括参数（通过 joinPoint.getArgs() 获取）等等。

@Pointcut 注解指定一个切面，定义需要拦截的东西，这里介绍两个常用的表达式：一个是使用 execution()，另一个是使用 annotation()。

annotation()使用示例：
```
@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
public void annotationCut() {}
```
