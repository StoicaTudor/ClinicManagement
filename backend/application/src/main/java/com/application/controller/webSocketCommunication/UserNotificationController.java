package com.application.controller.webSocketCommunication;

import com.core.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.swing.*;

import java.util.Objects;

import static com.infrastructure.entity.UserType.DOCTOR;

@Controller
public class UserNotificationController {

    private final UserService userService;

    public UserNotificationController(UserService userService) {
        this.userService = userService;
    }

//    @MessageMapping(UrlMappings.MESSAGE_BROKER + UrlMappings.)
//    @SendTo
    public void notify(String message, int userId) {

        if (Objects.requireNonNull(userService.getById(userId).orElse(null)).getUserType() == DOCTOR) {
            JOptionPane.showMessageDialog(null, message);
        }
    }
}