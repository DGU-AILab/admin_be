package DGU_AI_LAB.admin_be.domain.alarm.service;

import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlarmService {

    @Value("${slack.webhook-url}")
    private String webhookUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    public void sendAlert(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payload = Map.of("text", message);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            System.err.println("Slack 알림 전송 실패: " + response.getStatusCode());
        } else {
            System.out.println("Slack 알림 전송 성공");
        }
    }
}