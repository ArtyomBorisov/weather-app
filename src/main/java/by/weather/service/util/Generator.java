package by.weather.service.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Generator {
    public LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }
}
