package by.weather.service.util;

import by.weather.dao.model.Weather;
import by.weather.service.dto.WeatherActualDto;
import by.weather.service.dto.WeatherExternalDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static by.weather.util.WeatherData.*;
import static org.junit.jupiter.api.Assertions.*;

class WeatherMapperTest {

    private final WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);

    @Test
    void shouldMapAllFieldsCorrectlyWhenWeatherMapToWeatherActualDto() {
        Weather weather = createWeather();
        weather.setDateTime(DEFAULT_DATE_TIME);
        WeatherActualDto expectedResult = createWeatherActualDto();

        WeatherActualDto actualResult = weatherMapper.weatherToWeatherActualDto(weather);

        assertNotNull(actualResult);
        assertNull(actualResult.getLocation());
        assertEquals(expectedResult.getTemperature(), actualResult.getTemperature());
        assertEquals(expectedResult.getWindMph(), actualResult.getWindMph());
        assertEquals(expectedResult.getPressureMb(), actualResult.getPressureMb());
        assertEquals(expectedResult.getHumidity(), actualResult.getHumidity());
        assertEquals(expectedResult.getCondition(), actualResult.getCondition());
        assertEquals(expectedResult.getDateTime(), actualResult.getDateTime());
    }

    @Test
    void shouldMapAllFieldsCorrectlyWhenWeatherExternalDtoMapToWeather() {
        WeatherExternalDto weatherExternalDto = createWeatherExternalDto();
        Weather expectedResult = createWeather();

        Weather actualResult = weatherMapper.weatherExternalDtoToWeather(weatherExternalDto);

        assertNotNull(actualResult);
        assertNull(actualResult.getId());
        assertEquals(expectedResult.getDateTime(), actualResult.getDateTime());
        assertEquals(expectedResult.getTemperature(), actualResult.getTemperature());
        assertEquals(expectedResult.getWindMph(), actualResult.getWindMph());
        assertEquals(expectedResult.getPressureMb(), actualResult.getPressureMb());
        assertEquals(expectedResult.getHumidity(), actualResult.getHumidity());
        assertEquals(expectedResult.getCondition(), actualResult.getCondition());
    }
}
