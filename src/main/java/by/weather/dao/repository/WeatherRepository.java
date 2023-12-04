package by.weather.dao.repository;

import by.weather.dao.model.Weather;
import by.weather.service.dto.WeatherAverageDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    Optional<Weather> getFirstByOrderByDateTimeDesc();

    @Query("""
            SELECT new by.weather.service.dto.WeatherAverageDto(
                ROUND(AVG(w.temperature), 2),
                ROUND(AVG(w.windMph), 2),
                ROUND(AVG(w.pressureMb), 2),
                ROUND(AVG(w.humidity), 2),
                MIN(w.dateTime),
                MAX(w.dateTime)
            )
            FROM Weather AS w
            WHERE DATE(w.dateTime) BETWEEN ?1 and ?2
            """)
    WeatherAverageDto getAverageBetweenFromAndTo(LocalDate from, LocalDate to);
}