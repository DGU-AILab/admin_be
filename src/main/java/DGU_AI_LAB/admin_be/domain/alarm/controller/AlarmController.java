package DGU_AI_LAB.admin_be.domain.alarm.controller;

import DGU_AI_LAB.admin_be.domain.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/api/alert/send")
    public ResponseEntity<String> sendAlert(@RequestParam("message") String message) {
        alarmService.sendSlackAlert(message);
        return ResponseEntity.ok("Alert sent to Slack");
    }
}
