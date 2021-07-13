package com.movie.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author : 765819328@qq.com
 * @version :
 * @date : Created in 2021/7/12 23:03
 * @description: 启动类 修改
 * @modified By:
 */
@SpringBootApplication
public class ReptileSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReptileSpringApplication.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
