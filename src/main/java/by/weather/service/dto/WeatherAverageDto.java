package by.weather.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@JsonPropertyOrder({"average_temp", "average_wind_mph", "average_pressure_mb", "average_humidity",
        "location", "existing_from", "existing_to"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WeatherAverageDto {
    private String location;
    private final Double averageTemp;
    private final Double averageWindMph;
    private final Double averagePressureMb;
    private final Double averageHumidity;
    private final LocalDateTime existingFrom;
    private final LocalDateTime existingTo;
}
