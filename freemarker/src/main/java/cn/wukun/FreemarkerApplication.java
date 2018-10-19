package cn.wukun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.wukun.mybatis.dao")
public class FreemarkerApplication {
    public static void main(String[] args){
        SpringApplication.run(FreemarkerApplication.class, args);
    }
}
