## 一. 全局异常拦截
新建一个全局异常处理类，然后加上 @ControllerAdvice 注解即可拦截项目中抛出的异常，如下代码：
```
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    // 打印log
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // ……
}
```
@ControllerAdvice 注解包含了 @Component 注解，说明在 Spring Boot 启动时，也会把该类作为组件交给 Spring 来管理。

除此之外，该注解还有个 basePackages 属性，该属性用来拦截哪个包中的异常信息，一般我们不指定这个属性，我们拦截项目工程中的所有异常。

@ResponseBody 注解是为了异常处理完之后给调用方输出一个 JSON 格式的封装数据。

使用拦截HttpMessageNotReadableException异常作为示例，其代码如下：
```
/**
* 缺少请求参数异常
* @param ex HttpMessageNotReadableException
* @return
*/
@ExceptionHandler(MissingServletRequestParameterException.class)
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public JsonResult handleHttpMessageNotReadableException(
    MissingServletRequestParameterException ex) {
    logger.error("缺少请求参数，{}", ex.getMessage());
    return new JsonResult("400", "缺少必要的请求参数");
}
```
在方法上通过 @ExceptionHandler 注解来指定具体的异常，然后在方法中处理该异常信息，最后将结果通过统一的 JSON 结构体返回给调用者。

## 二. 自定义异常
### 2.1 定义异常信息
由于在业务中，有很多异常，针对不同的业务，可能给出的提示信息不同，所以为了方便项目异常信息管理，我们一般会定义一个异常信息枚举类。比如：
```
/**
 * 业务异常提示信息枚举类
 * @author shengwu ni
 */
public enum BusinessMsgEnum {
    /** 参数异常 */
    PARMETER_EXCEPTION("102", "参数异常!"),
    /** 等待超时 */
    SERVICE_TIME_OUT("103", "服务调用超时！"),
    /** 参数过大 */
    PARMETER_BIG_EXCEPTION("102", "输入的图片数量不能超过50张!"),
    /** 500 : 一劳永逸的提示也可以在这定义 */
    UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员！");
    // 还可以定义更多的业务异常

    /**
     * 消息码
     */
    private String code;
    /**
     * 消息内容
     */
    private String msg;

    private BusinessMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    // set get方法
}
```

### 2.2 拦截自定义异常
我们可以定义一个业务异常，当出现业务异常时，我们就抛这个自定义的业务异常即可。比如我们定义一个 BusinessErrorException 异常，如下：
```
/**
 * 自定义业务异常
 * @author shengwu ni
 */
public class BusinessErrorException extends RuntimeException {

    private static final long serialVersionUID = -7480022450501760611L;

    /**
     * 异常码
     */
    private String code;
    /**
     * 异常提示信息
     */
    private String message;

    public BusinessErrorException(BusinessMsgEnum businessMsgEnum) {
        this.code = businessMsgEnum.code();
        this.message = businessMsgEnum.msg();
    }
    // get set方法
}
```
在构造方法中，传入我们上面自定义的异常枚举类，在项目中，如果有新的异常信息需要添加，我们直接在枚举类中添加即可，很方便，做到统一维护，在拦截该异常时获取即可。
```
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 拦截业务异常，返回业务异常信息
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(BusinessErrorException ex) {
        String code = ex.getCode();
        String message = ex.getMessage();
        return new JsonResult(code, message);
    }
}
```
