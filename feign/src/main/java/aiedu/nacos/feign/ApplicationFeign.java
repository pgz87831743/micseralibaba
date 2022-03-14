package aiedu.nacos.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author pengmf
 * @since 2021/12/30
 */
@SpringBootApplication
@EnableFeignClients
public class ApplicationFeign {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationFeign.class, args);
    }


}
