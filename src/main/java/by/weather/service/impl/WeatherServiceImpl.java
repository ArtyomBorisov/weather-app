package by.weather.service.impl;

import by.weather.dao.model.Weather;
import by.weather.dao.repository.WeatherRepository;
import by.weather.api.exception.WeatherNotFound;
import by.weather.service.WeatherService;
import by.weather.service.dto.WeatherActualDto;
import by.weather.service.dto.WeatherAverageDto;
import by.weather.service.dto.WeatherExternalDto;
import by.weather.service.util.ApplicationParams;
import by.weather.service.util.Generator;
import by.weather.service.util.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;
    private final ApplicationParams applicationParams;
    private final Generator generator;

    @Override
    public WeatherActualDto getActualWeather() {
        Weather weatherActualEntity = weatherRepository.getFirstByOrderByDateTimeDesc()
                .orElseThrow(WeatherNotFound::new);
        WeatherActualDto weatherActualDto = weatherMapper.weatherToWeatherActualDto(weatherActualEntity);
        String location = applicationParams.getLocation();
        weatherActualDto.setLocation(location);
        return weatherActualDto;
    }

    @Override
    public WeatherAverageDto getAverageWeather(LocalDate from, LocalDate to) {
        WeatherAverageDto weatherAverageDto = weatherRepository.getAverageBetweenFromAndTo(from, to);
        if(weatherAverageDto == null || weatherAverageDto.getAverageTemp() == null) {
            throw new WeatherNotFound();
        }
        String location = applicationParams.getLocation();
        weatherAverageDto.setLocation(location);
        return weatherAverageDto;
    }

    @Override
    @Transactional
    public WeatherActualDto saveActualWeather(WeatherExternalDto weatherExternalDto) {
        Weather weatherForSaving = weatherMapper.weatherExternalDtoToWeather(weatherExternalDto);
        LocalDateTime dateTimeNow = generator.getDateTimeNow();
        weatherForSaving.setDateTime(dateTimeNow);
        Weather savedWeather = weatherRepository.save(weatherForSaving);
        WeatherActualDto weatherActualDto = weatherMapper.weatherToWeatherActualDto(savedWeather);
        String location = applicationParams.getLocation();
        weatherActualDto.setLocation(location);
        return weatherActualDto;
    }
}
