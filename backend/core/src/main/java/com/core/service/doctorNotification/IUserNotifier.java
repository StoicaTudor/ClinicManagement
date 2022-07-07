package com.core.service.doctorNotification;

import com.core.dto.consultation.ConsultationGetDto;

import java.io.IOException;

public interface IUserNotifier {
    void sendNotification(ConsultationGetDto consultationGetDto) throws IOException;
}
