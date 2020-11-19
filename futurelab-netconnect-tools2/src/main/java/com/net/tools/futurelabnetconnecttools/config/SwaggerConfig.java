package com.net.tools.futurelabnetconnecttools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    /**
     * 接口版本号
     */
    private final String version = "v1";
    /**
     * 接口大标题
     */
    private final String title = "项目工具接口文档";
    /**
     * 具体的描述
     */
    private final String description = "项目工具接口文档";
    /**
     * 服务说明url
     */
    private final String termsOfServiceUrl = "";
    /**
     * licence
     */
    private final String license = "MIT";
    /**
     * licnce url
     */
    private final String licenseUrl = "https://license.org/";
    /**
     * 接口作者联系方式
     */
    private final Contact contact = new Contact("SunJ", "", "");

    @Bean
    public Docket buildDocket() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.net.tools.futurelabnetconnecttools.*.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl(termsOfServiceUrl)
                .description(description)
                .version(version)
                .license(license)
                .licenseUrl(licenseUrl)
                .contact(contact)
                .build();

    }

}
