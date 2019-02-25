package com.ximalaya.ad.ci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 这个地方要重新注入一下资源文件，不然不会注入资源的，也没有注入requestHandlerMappping,相当于xml配置的
     * <!--swagger资源配置-->
     * <mvc:resources location="classpath:/META-INF/resources/" mapping="swagger-ui.html"/>
     * <mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> parameterArrayList = new ArrayList<>();
        tokenPar.name("disable-access-token").description("免校验令牌").modelRef(new ModelRef("string")).parameterType("header").required(true)
                .defaultValue("disable-access-token").build();
        parameterArrayList.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ximalaya.ad.ci.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterArrayList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api集成测试")
                .description("api集成测试")
                .termsOfServiceUrl("")
                .contact("资源管理中心")
                .version("1.0")
                .build();
    }

}
