package by.weather.service.impl;

import by.weather.api.exception.WeatherNotFound;
import by.weather.dao.model.Weather;
import by.weather.dao.repository.WeatherRepository;
import by.weather.service.dto.WeatherActualDto;
import by.weather.service.dto.WeatherAverageDto;
import by.weather.service.dto.WeatherExternalDto;
import by.weather.service.util.ApplicationParams;
import by.weather.service.util.Generator;
import by.weather.service.util.WeatherMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static by.weather.util.WeatherData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    private final static String LOCATION = "minsk";

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherMapper weatherMapper;

    @Mock
    private ApplicationParams applicationParams;

    @Mock
    private Generator generator;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Nested
    class NestedTest {
        private final Weather WEATHER = createWeather();
        private final WeatherActualDto WEATHER_ACTUAL_DTO = createWeatherActualDto();
        private final WeatherAverageDto WEATHER_AVERAGE_DTO = createWeatherAverageDto();
        private final WeatherExternalDto WEATHER_EXTERNAL_DTO = createWeatherExternalDto();

        @BeforeEach
        void mockLocation() {
            when(applicationParams.getLocation()).thenReturn(LOCATION);
        }

        @Test
        void shouldReturnWeatherActualDto() {
            when(weatherRepository.getFirstByOrderByDateTimeDesc())
                    .thenReturn(Optional.of(WEATHER));
            when(weatherMapper.weatherToWeatherActualDto(WEATHER))
                    .thenReturn(WEATHER_ACTUAL_DTO);

            WeatherActualDto actualResult = weatherService.getActualWeather();

            assertNotNull(actualResult);
            assertEquals(LOCATION, actualResult.getLocation());
            assertEquals(WEATHER_ACTUAL_DTO.getTemperature(), actualResult.getTemperature());
            assertEquals(WEATHER_ACTUAL_DTO.getWindMph(), actualResult.getWindMph());
            assertEquals(WEATHER_ACTUAL_DTO.getPressureMb(), actualResult.getPressureMb());
            assertEquals(WEATHER_ACTUAL_DTO.getHumidity(), actualResult.getHumidity());
            assertEquals(WEATHER_ACTUAL_DTO.getCondition(), actualResult.getCondition());
            assertEquals(WEATHER_ACTUAL_DTO.getDateTime(), actualResult.getDateTime());
        }

        @ParameterizedTest
        @MethodSource("by.weather.service.impl.WeatherServiceImplTest#twoDates")
        void shouldReturnWeatherAverageDto(LocalDate from, LocalDate to) {
            when(weatherRepository.getAverageBetweenFromAndTo(from, to))
                    .thenReturn(WEATHER_AVERAGE_DTO);

            WeatherAverageDto actualResult = weatherService.getAverageWeather(from, to);

            assertNotNull(actualResult);
            assertEquals(LOCATION, actualResult.getLocation());
            assertEquals(WEATHER_AVERAGE_DTO.getAverageTemp(), actualResult.getAverageTemp());
            assertEquals(WEATHER_AVERAGE_DTO.getAverageWindMph(), actualResult.getAverageWindMph());
            assertEquals(WEATHER_AVERAGE_DTO.getAveragePressureMb(), actualResult.getAveragePressureMb());
            assertEquals(WEATHER_AVERAGE_DTO.getAverageHumidity(), actualResult.getAverageHumidity());
            assertEquals(WEATHER_AVERAGE_DTO.getExistingFrom(), actualResult.getExistingFrom());
            assertEquals(WEATHER_AVERAGE_DTO.getExistingTo(), actualResult.getExistingTo());
        }

        @Test
        void shouldReturnSavedWeatherActual() {
            when(weatherMapper.weatherExternalDtoToWeather(WEATHER_EXTERNAL_DTO))
                    .thenReturn(WEATHER);
            when(weatherRepository.save(WEATHER))
                    .thenReturn(WEATHER);
            when(weatherMapper.weatherToWeatherActualDto(WEATHER))
                    .thenReturn(WEATHER_ACTUAL_DTO);
            when(generator.getDateTimeNow())
                    .thenReturn(DEFAULT_DATE_TIME);

            WeatherActualDto actualResult = weatherService.saveActualWeather(WEATHER_EXTERNAL_DTO);

            assertNotNull(actualResult);
            assertEquals(LOCATION, actualResult.getLocation());
            assertEquals(WEATHER_ACTUAL_DTO.getTemperature(), actualResult.getTemperature());
            assertEquals(WEATHER_ACTUAL_DTO.getWindMph(), actualResult.getWindMph());
            assertEquals(WEATHER_ACTUAL_DTO.getPressureMb(), actualResult.getPressureMb());
            assertEquals(WEATHER_ACTUAL_DTO.getHumidity(), actualResult.getHumidity());
            assertEquals(WEATHER_ACTUAL_DTO.getCondition(), actualResult.getCondition());
            assertEquals(WEATHER_ACTUAL_DTO.getDateTime(), actualResult.getDateTime());
        }
    }

    @Test
    void shouldThrowExceptionIfWeatherActualNotFound() {
        assertThrows(WeatherNotFound.class, weatherService::getActualWeather);
    }

    @ParameterizedTest
    @MethodSource("twoDates")
    void shouldThrowExceptionIfWeatherAverageNotFound(LocalDate from, LocalDate to) {
        assertThrows(WeatherNotFound.class, () -> weatherService.getAverageWeather(from, to));
    }

    static Stream<Arguments> twoDates() {
        return Stream.of(
                arguments(LocalDate.of(2014, 2, 2), LocalDate.of(2015, 4, 4)),
                arguments(LocalDate.now(), LocalDate.now().plusDays(2))
        );
    }
}
