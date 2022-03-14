package aiedu.nacos.security.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengmf
 * @since 2022/1/11
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {



    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //包路径
                .apis(RequestHandlerSelectors.basePackage("aiedu.nacos.security"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(unifiedAuth());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("micseralibaba")
//                .description("description")
                //服务条款网址
                .version("0.0")
                .contact(new Contact("测试", "https://www.baidu.com", ""))
                .build();
    }


    /**
     * 统一参数设置
     *
     * @return
     */
    private static List<ApiKey> unifiedAuth() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeys;
    }

}
