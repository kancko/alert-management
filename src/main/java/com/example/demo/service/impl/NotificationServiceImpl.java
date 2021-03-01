package com.example.demo.service.impl;

import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.util.CollectionUtils.toMultiValueMap;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.NotificationConfig;
import com.example.demo.converter.AlertConverter;
import com.example.demo.entity.Alert;
import com.example.demo.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final RestTemplate restTemplate;
    private final AlertConverter alertConverter;
    private final ConditionService conditionService;
    private final NotificationConfig notificationConfig;

    @Override
    public void fireNotification(Alert alert) {
        if (conditionService.test(alert)) {
            var headers = toMultiValueMap(Map.of(CONTENT_TYPE, List.of(APPLICATION_JSON)));
            var request = new HttpEntity<>(alertConverter.convert(alert), headers);
            restTemplate.postForLocation(notificationConfig.getUrl(), request);
        }
    }
}
