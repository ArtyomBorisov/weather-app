package by.weather.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherExternalDto {
    private CurrentWeather current;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(setterPrefix = "set")
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CurrentWeather {
        private Double tempC;
        private Double windMph;
        private Double pressureMb;
        private Integer humidity;
        private Condition condition;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Condition {
        private String text;
    }
}
