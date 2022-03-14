package aiedu.nacos.feign;

import aiedu.nacos.feign.client.DemoFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author pengmf
 * @since 2021/12/30
 */
@SpringBootTest(classes = {ApplicationFeign.class})
public class ApplicationFeignTest {

    @Resource
    DemoFeignClient demoFeignClient;


    @Test
    void testOpenFeign() {
        System.out.println(demoFeignClient.echo("asfjashfk"));
    }
}
