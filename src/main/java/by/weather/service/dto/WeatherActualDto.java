package by.weather.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "set")
@JsonPropertyOrder({"temperature", "wind_mph", "pressure_mb", "humidity", "condition", "location", "date_time"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WeatherActualDto {
    private String location;
    private LocalDateTime dateTime;
    private final Double temperature;
    private final Double windMph;
    private final Double pressureMb;
    private final Integer humidity;
    private final String condition;
}
