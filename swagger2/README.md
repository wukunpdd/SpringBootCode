## 一. swagger2依赖和配置
需要的依赖：
```
compile('io.springfox:springfox-swagger2:2.2.2')
compile('io.springfox:springfox-swagger-ui:2.2.2')
```
需要的配置代码：
```
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定构建api文档的详细信息的方法：apiInfo()
                .apiInfo(apiInfo())
                .select()
                // 指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("cn.wukun.web"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建api文档的详细信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("Spring Boot集成Swagger2接口总览")
                // 设置接口描述
                .description("方方是我")
                // 设置联系方式
                .contact("吴堃，" + "https://blog.csdn.net/qq_22314145")
                // 设置版本
                .version("1.0")
                // 构建
                .build();
    }
}
```

## 二.swagger2的主要注解
### 2.1 应用在实体类中
@ApiModel 注解用于实体类，表示对类进行说明，用于参数用实体类接收。

@ApiModelProperty 注解用于类中属性，表示对 Model 属性的说明或者数据操作更改。

### 2.2应用在Controller层
@Api 注解用于类上，表示标识这个类是 Swagger 的资源。

@ApiOperation 注解用于方法，表示一个 HTTP 请求的操作。

@ApiParam 注解用于参数上，用来标明参数信息。
