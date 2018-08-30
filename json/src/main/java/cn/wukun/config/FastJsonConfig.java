package cn.wukun.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 没有 @Configuration 注解此配置代码是无效的
 * 此配置文件是 fastjosn 作为Spring Boot的json配置文件的情况下使用
 * 下面的代码是需要 fastjson 对应jar包才会起作用的，本人使用的 jackson
 * 而没使用fastjson.jar，故注释下面代码
 */
public class FastJsonConfig extends WebMvcConfigurationSupport {

    /**
     * 使用阿里 fastjson 作为JSON MessageConverter
     * @param converters
     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(
//                // 保留map空的字段
//                SerializerFeature.WriteMapNullValue,
//                // 将String类型的null转成""
//                SerializerFeature.WriteNullStringAsEmpty,
//                // 将Number类型的null转成0
//                SerializerFeature.WriteNullNumberAsZero,
//                // 将List类型的null转成[]
//                SerializerFeature.WriteNullListAsEmpty,
//                // 将Boolean类型的null转成false
//                SerializerFeature.WriteNullBooleanAsFalse,
//                // 避免循环引用
//                SerializerFeature.DisableCircularReferenceDetect);
//
//        converter.setFastJsonConfig(config);
//        converter.setDefaultCharset(Charset.forName("UTF-8"));
//        List<MediaType> mediaTypeList = new ArrayList<>();
//        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
//        mediaTypeList.add(MediaType.APPLICATION_JSON);
//        converter.setSupportedMediaTypes(mediaTypeList);
//        converters.add(converter);
//    }
}
