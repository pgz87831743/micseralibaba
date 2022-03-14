package aiedu.nacos.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author pengmf
 * @since 2022/1/5
 */
@Component
@FeignClient(name = "demo", url = "http://localhost:8081/", fallback = DemoFeignClient.DemoFeignClientFallback.class)
public interface DemoFeignClient {

    @GetMapping(value = "/echo/{string}")
    String echo(@PathVariable("string") String string);


    @GetMapping(value = "/echo1")
    String echo1();

    @Component
    class DemoFeignClientFallback implements DemoFeignClient {

        @Override
        public String echo(String string) {
            return "出问题了";
        }

        @Override
        public String echo1() {
            return "出问题了";
        }
    }




}
