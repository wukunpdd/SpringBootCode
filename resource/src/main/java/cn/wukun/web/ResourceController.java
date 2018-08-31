package cn.wukun.web;

import cn.wukun.config.MicroServiceUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Value("${url.orderUrl}")
    private String orderUrl;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private MicroServiceUrl microServiceUrl;

    @RequestMapping("/test")
    public void test(){
        logger.info("获取的订单服务地址为：{}", orderUrl);
        logger.info("serverPort：{}", serverPort);
    }

    @RequestMapping("/testMicro")
    public void testMicro(){
        logger.info("地址一：{}", microServiceUrl.getOrderUrl());
        logger.info("地址二：{}", microServiceUrl.getShoppingUrl());
        logger.info("地址三：{}", microServiceUrl.getUserUrl());
    }
}
