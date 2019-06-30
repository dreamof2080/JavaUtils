package com.jeffrey.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 加了ApiOperation注解的类，才生成接口文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                // 包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.jeffrey"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
                .title("JavaUtils")
                .description("utils开发文档")
                .version("1.0")
                .build();
    }
}
