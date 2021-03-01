package com.example.demo.rest;

import static org.apache.camel.model.rest.RestBindingMode.json;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AlertDto;
import com.example.demo.entity.Alert;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlertRest extends RouteBuilder {

    @Override
    public void configure() {
        rest("/alert").tag("Alert API")
                .consumes(APPLICATION_JSON_VALUE)
                .produces(APPLICATION_JSON_VALUE)
                .bindingMode(json)

                .get()
                .route()
                .to("bean:alertService?method=findAll")
                .convertBodyTo(AlertDto[].class)
                .endRest()

                .post()
                .type(AlertDto.class)
                .route()
                .convertBodyTo(Alert.class)
                .to("bean:alertService?method=save")
                .convertBodyTo(AlertDto.class)
                .endRest()

                .put()
                .type(AlertDto.class)
                .route()
                .convertBodyTo(Alert.class)
                .to("bean:alertService?method=update")
                .convertBodyTo(AlertDto.class)
                .endRest()

                .delete("/{id}")
                .route()
                .to("bean:alertService?method=delete(${headers.id})")
                .endRest();
    }
}
