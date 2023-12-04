package by.weather.service.util;

import by.weather.dao.model.Weather;
import by.weather.service.dto.WeatherActualDto;
import by.weather.service.dto.WeatherExternalDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface WeatherMapper {

    @Mapping(target = "location", ignore = true)
    WeatherActualDto weatherToWeatherActualDto(Weather weather);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "temperature", source = "current.tempC")
    @Mapping(target = "windMph", source = "current.windMph")
    @Mapping(target = "pressureMb", source = "current.pressureMb")
    @Mapping(target = "humidity", source = "current.humidity")
    @Mapping(target = "condition", source = "current.condition.text")
    Weather weatherExternalDtoToWeather(WeatherExternalDto weatherExternalDto);
}
