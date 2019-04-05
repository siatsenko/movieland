package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.entity.report.Report;
import com.siatsenko.movieland.service.ReportSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultReportSenderService implements ReportSenderService {

    @Value("${reports.mailList}")
    private String mailList;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void send(Report report) {
        String to = mailList;
        String subject = "Report #" + report.getId() + " is done";
        String text = report.getLink();
        sendSimpleMessage(to, subject, text);
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


}
