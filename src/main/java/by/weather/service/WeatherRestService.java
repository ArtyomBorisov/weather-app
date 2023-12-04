package by.weather.service;

import by.weather.service.dto.WeatherExternalDto;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface WeatherRestService {
    @GetExchange
    WeatherExternalDto get();
}
