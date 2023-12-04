package by.weather.service.dto;

import by.weather.service.util.Period;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public record PeriodParams(
        @Getter @NotNull(message = "Field 'from' cannot be null") LocalDate from,
        @Getter @NotNull(message = "Field 'to' cannot be null") LocalDate to
) implements Period<LocalDate> {
}
