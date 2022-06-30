package com.elcompbase.config;

import com.elcompbase.config.property.ApplicationProperty;
import com.elcompbase.model.enums.Auth;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final ApplicationProperty applicationProperty;

    @Bean
    public OpenAPI openApi() {
        OpenAPI openAPI = new OpenAPI().components(new Components().addSecuritySchemes(Auth.BEARER.getMeaning().strip(), new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name(Auth.BEARER.getMeaning())))
                .info(new Info().title("Elcomp Base Application")
                        .contact(new Contact().name("Eugene Krivtsov")).version("1.0.0"));
        if (StringUtils.isNoneBlank(applicationProperty.url())) {
            openAPI.servers(List.of(new Server().url(applicationProperty.url() + applicationProperty.apiPath())));
        }
        return openAPI;
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/api/v1/user/**")
                .build();
    }
}
