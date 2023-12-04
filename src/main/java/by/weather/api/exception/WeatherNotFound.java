package by.weather.api.exception;

public class WeatherNotFound extends RuntimeException {
    public WeatherNotFound() {
        super("Weather data not found");
    }
}
