package cn.wukun.web;

import cn.wukun.interceptor.UnInterception;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping("/test")
    public String test() {
        return "hello";
    }

    @UnInterception
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
