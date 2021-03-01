package com.example.demo.config;

import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;
import static org.apache.camel.model.rest.RestBindingMode.json;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.springboot.ServletMappingConfiguration;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CamelRouteConfig extends RouteBuilder {

    private final ServletMappingConfiguration mappingConfiguration;

    @Override
    public void configure() {
        restConfiguration()
                .enableCORS(true)
                .component("servlet")

                .contextPath(substringBefore(mappingConfiguration.getContextPath(), "/*"))
                .apiContextPath("/api-doc")
                .apiContextRouteId("doc-api")

                .apiProperty("api.title", "Alert Management Service")
                .apiProperty("api.version", "v1.0")
                .bindingMode(json);

        rest().apiDocs(false)
                .get("/swagger-ui")
                .route()
                .setHeader("Location", simple("/webjars/swagger-ui/index.html?url=/api/api-doc"))
                .setHeader(HTTP_RESPONSE_CODE, () -> 301);
    }
}
