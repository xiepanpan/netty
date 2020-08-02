package com.xiepanpan;

import com.xiepanpan.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: xiepanpan
 * @Date: 2020/8/1 0001
 * @Description:  启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xiepanpan.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(0,0);
    }
}