package by.weather.config;

import by.weather.service.WeatherRestService;
import by.weather.service.util.ApplicationParams;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@AllArgsConstructor
public class WebClientConfig {

    private final ApplicationParams applicationParams;

    @Bean
    public WebClient weatherWebClient() {
        String location = applicationParams.getLocation();
        String keyForApi = applicationParams.getKeyForApi();
        String url = String.format("%s%s", "https://weatherapi-com.p.rapidapi.com/current.json?q=", location);

        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Key", keyForApi)
                .build();
    }

    @Bean
    public WeatherRestService weatherRestService() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(weatherWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(WeatherRestService.class);
    }
}
