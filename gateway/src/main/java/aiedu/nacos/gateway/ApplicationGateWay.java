package aiedu.nacos.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author pengmf
 * @since 2021/12/30
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationGateWay {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationGateWay.class, args);
    }


}
