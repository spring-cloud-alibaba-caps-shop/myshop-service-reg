package com.caps.myshop.service.reg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * @author caps
 * @Auther: caps
 * @Date: 2019/5/14 23:24
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "org.caps.myshop.commons.mapper")
public class MyShopServiceRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyShopServiceRegApplication.class, args);
    }
}
