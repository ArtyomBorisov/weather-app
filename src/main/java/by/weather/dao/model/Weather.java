package by.weather.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Double temperature;

    @Column(name = "wind_mph", nullable = false)
    private Double windMph;

    @Column(name = "pressure_mb", nullable = false)
    private Double pressureMb;

    @Column(nullable = false)
    private Integer humidity;

    @Column(name = "weather_condition", nullable = false)
    private String condition;
}
