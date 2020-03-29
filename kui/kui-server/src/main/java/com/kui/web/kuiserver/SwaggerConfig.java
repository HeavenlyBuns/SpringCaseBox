package com.kui.web.kuiserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger
 */
@Configuration
@EnableSwagger2
//@Profile({"dev","test","release"})
public class SwaggerConfig {

	 @Bean
	 public Docket createRestApi() {
	     return new Docket(DocumentationType.SWAGGER_2)
	     .apiInfo(apiInfo())
	     .select()
	     .apis(RequestHandlerSelectors.basePackage("com.kui.web.kuiserver"))
	     .paths(PathSelectors.any())
	     .build();
	 }

	 @Bean
	 public ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
				 .title("奎哥千万项目接口文档(Please refer to xs for questions)")
				 .description("奎哥千万项目接口文档")
				 .version("1.0")
				 .build();
	 }
}