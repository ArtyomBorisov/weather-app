package by.weather.api.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonPropertyOrder({"status_code", "message", "date_time"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ErrorResponse(int statusCode, String message, String dateTime) {
}
