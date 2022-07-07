package com.core.service.doctorNotification;

import com.core.dto.consultation.ConsultationGetDto;
import com.core.service.FileService;
import com.core.service.ScriptService;
import com.infrastructure.entity.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class DoctorNotificationService implements IUserNotifier {

    private final RestTemplate restTemplate;

    public DoctorNotificationService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public User getUserDetails(String name) {
        return restTemplate.getForObject("/{name}/details", User.class, name);
    }

    @Override
    public void sendNotification(ConsultationGetDto consultationGetDto) throws IOException {

        String script = FileService.readFileFromResources("scripts/StompClient.js");

        try {
            ScriptService.runJavaScript(script, "notifyEndpoint", getMessage(consultationGetDto), "/app/hello");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getMessage(ConsultationGetDto consultationGetDto) {
        return
                "You have an appointment at date " +
                        consultationGetDto.getDate() +
                        "with patient " +
                        consultationGetDto.getPatient().getName();
    }
}
