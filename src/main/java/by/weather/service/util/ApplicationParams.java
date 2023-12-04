package by.weather.service.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class ApplicationParams {
    @Value("${properties.location}")
    private String location;

    @Value("${properties.period}")
    private Long period;

    @Value("${properties.rapid-key}")
    private String keyForApi;
}
