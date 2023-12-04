package by.weather.api.controller;

import by.weather.service.WeatherService;
import by.weather.service.annotation.PeriodLocalDateValidation;
import by.weather.service.dto.PeriodParams;
import by.weather.service.dto.WeatherActualDto;
import by.weather.service.dto.WeatherAverageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Weather Controller")
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/actual")
    @Operation(summary = "Get actual weather")
    public WeatherActualDto getActualWeather() {
        WeatherActualDto weatherActualDto = weatherService.getActualWeather();
        log.debug("Getting actual weather: {}", weatherActualDto);
        return weatherActualDto;
    }

    @PostMapping(value = "/average")
    @Operation(summary = "Get average weather values during given period")
    public WeatherAverageDto getAverageWeather(
            @RequestBody @Valid @PeriodLocalDateValidation PeriodParams params
    ) {
        WeatherAverageDto weatherAverageDto = weatherService.getAverageWeather(params.getFrom(), params.getTo());
        log.debug("Getting average weather: {}", weatherAverageDto);
        return weatherAverageDto;
    }
}
