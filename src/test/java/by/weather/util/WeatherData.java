package by.weather.util;

import by.weather.dao.model.Weather;
import by.weather.service.dto.WeatherActualDto;
import by.weather.service.dto.WeatherAverageDto;
import by.weather.service.dto.WeatherExternalDto;

import java.time.LocalDateTime;

public final class WeatherData {
    public final static Long DEFAULT_ID = 5L;
    public final static LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.of(2023, 10, 10, 10, 10);
    public final static Double DEFAULT_TEMPERATURE = 25.5;
    public final static Double DEFAULT_WIND_MPH = 5.5;
    public final static Double DEFAULT_PRESSURE_MB = 1001.1;
    public final static Integer DEFAULT_HUMIDITY = 80;
    public final static String DEFAULT_CONDITION = "Clear";

    public static Weather createWeather() {
        return Weather.builder()
                .setId(DEFAULT_ID)
                .setDateTime(null)
                .setTemperature(DEFAULT_TEMPERATURE)
                .setWindMph(DEFAULT_WIND_MPH)
                .setPressureMb(DEFAULT_PRESSURE_MB)
                .setHumidity(DEFAULT_HUMIDITY)
                .setCondition(DEFAULT_CONDITION)
                .build();
    }

    public static WeatherActualDto createWeatherActualDto() {
        return WeatherActualDto.builder()
                .setTemperature(DEFAULT_TEMPERATURE)
                .setWindMph(DEFAULT_WIND_MPH)
                .setPressureMb(DEFAULT_PRESSURE_MB)
                .setHumidity(DEFAULT_HUMIDITY)
                .setCondition(DEFAULT_CONDITION)
                .setDateTime(DEFAULT_DATE_TIME)
                .build();
    }

    public static WeatherExternalDto createWeatherExternalDto() {
        WeatherExternalDto.CurrentWeather currentWeather
                = WeatherExternalDto.CurrentWeather.builder()
                .setTempC(DEFAULT_TEMPERATURE)
                .setWindMph(DEFAULT_WIND_MPH)
                .setPressureMb(DEFAULT_PRESSURE_MB)
                .setHumidity(DEFAULT_HUMIDITY)
                .setCondition(new WeatherExternalDto.Condition(DEFAULT_CONDITION))
                .build();

        return new WeatherExternalDto(currentWeather);
    }

    public static WeatherAverageDto createWeatherAverageDto() {
        return WeatherAverageDto.builder()
                .setAverageTemp(DEFAULT_TEMPERATURE)
                .setAverageWindMph(DEFAULT_WIND_MPH)
                .setAveragePressureMb(DEFAULT_PRESSURE_MB)
                .setAverageHumidity(Double.valueOf(DEFAULT_HUMIDITY))
                .setExistingFrom(DEFAULT_DATE_TIME)
                .setExistingTo(DEFAULT_DATE_TIME.plusDays(1))
                .build();
    }
}
