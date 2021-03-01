package com.example.demo.service;

import com.example.demo.entity.Alert;

public interface NotificationService {

    void fireNotification(Alert alert);
}
