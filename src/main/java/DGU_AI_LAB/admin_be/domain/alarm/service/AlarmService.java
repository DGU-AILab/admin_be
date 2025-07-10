package DGU_AI_LAB.admin_be.domain.alarm.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class AlarmService {

    @Value("${slack-webhook-url.monitoring}")
    private String defaultWebhookUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSlackAlert(String message) {
        sendSlackAlert(message, null);
    }

    public void sendSlackAlert(String message, String webhookUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payload = Map.of("text", message);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        String urlToUse = (webhookUrl != null && !webhookUrl.isEmpty()) ? webhookUrl : defaultWebhookUrl;

        ResponseEntity<String> response = restTemplate.postForEntity(urlToUse, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            log.debug("Slack 알림 전송 실패: {}", response.getStatusCode());
        } else {
            log.debug("Slack 알림 전송 성공");
        }
    }
    public void sendMailAlert(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.printf("메일 전송 성공: 수신자=%s, 제목=%s%n", to, subject);
    }
}