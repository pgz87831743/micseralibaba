package aiedu.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pengmf
 * @since 2021/12/30
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationProvider {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider.class, args);
    }


    @Resource
    HttpServletRequest request;


    @RestController
    public class EchoController {
        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable("string") String string) {

            int a=1/0;

            return "Hello Nacos Discovery " + string;
        }


        @GetMapping(value = "/echo1")
        public String echo1() {
            return "Hello Nacos Discovery ";
        }
    }
}
