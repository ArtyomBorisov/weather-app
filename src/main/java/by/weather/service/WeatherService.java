package by.weather.service;

import by.weather.service.dto.WeatherActualDto;
import by.weather.service.dto.WeatherAverageDto;
import by.weather.service.dto.WeatherExternalDto;

import java.time.LocalDate;

public interface WeatherService {
    WeatherActualDto getActualWeather();

    WeatherAverageDto getAverageWeather(LocalDate from, LocalDate to);

    WeatherActualDto saveActualWeather(WeatherExternalDto weatherExternalDto);
}
