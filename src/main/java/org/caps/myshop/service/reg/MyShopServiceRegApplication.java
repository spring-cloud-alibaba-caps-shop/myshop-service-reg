package org.caps.myshop.service.reg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * @author caps
 * @Auther: caps
 * @Date: 2019/5/14 23:24
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "org.caps.myshop")
@EnableDiscoveryClient
@MapperScan(basePackages = "org.caps.myshop.commons.mapper")
@EnableSwagger2
public class MyShopServiceRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyShopServiceRegApplication.class, args);
    }
}
