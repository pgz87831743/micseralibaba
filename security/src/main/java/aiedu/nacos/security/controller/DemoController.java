package aiedu.nacos.security.controller;

import aiedu.nacos.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author pengmf
 * @since 2022/1/4
 */
@RestController
@RequestMapping("api")
@Api(tags = "cs")
public class DemoController {


    @GetMapping("gen")
    public Result<String> getMapping() {
        return Result.success("hello GetMapping");
    }


    @PostMapping("gen")
    public String postMapping(@RequestParam("name") String name) {
        return "hello PostMapping " + name;
    }



    @DeleteMapping("gen/{name}")
    public String deleteMapping(@PathVariable("name") String name) {
        return "hello DeleteMapping " + name;
    }


    @PostMapping("gen/userInfo")
    public Authentication userInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
