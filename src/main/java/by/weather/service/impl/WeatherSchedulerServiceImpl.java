package by.weather.service.impl;

import by.weather.service.WeatherRestService;
import by.weather.service.WeatherService;
import by.weather.service.WeatherSchedulerService;
import by.weather.service.dto.WeatherExternalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherSchedulerServiceImpl implements WeatherSchedulerService {

    private final WeatherRestService weatherRestService;
    private final WeatherService weatherService;

    @Scheduled(fixedRateString = "${properties.period}")
    @Override
    public void saveActualWeather() {
        try {
            WeatherExternalDto weatherExternalDto = weatherRestService.get();
            log.debug("Getting weather information from api: {}", weatherExternalDto);
            weatherService.saveActualWeather(weatherExternalDto);
        } catch (WebClientException e) {
            log.error("Getting weather information from api was not successful", e);
        }

    }
}
