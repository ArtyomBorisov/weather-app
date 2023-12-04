package by.weather.api.controller;

import by.weather.api.exception.ErrorResponse;
import by.weather.api.exception.WeatherNotFound;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WeatherNotFound.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleNotFoundException(WeatherNotFound e) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                e.getMessage(),
                dateTimeNow()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException() {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid JSON data",
                dateTimeNow()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        String message = String.join("; ", errors);
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                dateTimeNow()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        String message = String.join("; ", errors);
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                dateTimeNow()
        );
    }

    private String dateTimeNow() {
        ZoneId zoneId = ZoneId.of("Europe/Minsk");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss O");
        return ZonedDateTime.now()
                .withZoneSameInstant(zoneId)
                .format(dateTimeFormatter);
    }
}
