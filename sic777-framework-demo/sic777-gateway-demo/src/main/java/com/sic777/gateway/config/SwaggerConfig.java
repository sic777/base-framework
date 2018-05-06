package com.sic777.gateway.config;

import java.util.ArrayList;
import java.util.List;

import com.sic777.gateway.constants.Sic777Constants;
import com.sic777.microserver.constants.MicroConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-06
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<Parameter> params = this.buildParams();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(Sic777Constants.BACK_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(params);
    }

    /**
     * 构建Header参数
     */
    private List<Parameter> buildParams() {
        List<Parameter> params = new ArrayList<Parameter>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(MicroConstants.ACCESS_TOKEN_FLAG)
                .description(MicroConstants.ACCESS_TOKEN_FLAG)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        params.add(tokenPar.build());
        return params;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("1.0")
                .build();
    }
}
