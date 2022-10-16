package com.example.docker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wxz
 * @date 13:58 2022/10/15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.swagger2.enabled}")
    private Boolean enabled;

    /**
     * @return springfox.documentation.spring.web.plugins.Docket
     * @author wxz
     * @date 14:09 2022/10/15
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(
                DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.docker"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return springfox.documentation.service.ApiInfo
     * @author wxz
     * @date 14:09 2022/10/15
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("哈哈哈哈" + "\t" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .description("docker-compose")
                .version("1.0")
                .termsOfServiceUrl("http://www.atguigu.com/")
                .build();
    }
}
