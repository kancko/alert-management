package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Alert;

public interface AlertService {

    Alert save(Alert alert);

    Alert update(Alert alert);

    void delete(Long alertId);

    List<Alert> findAll();
}
