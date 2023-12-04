package by.weather.service.annotation;

import by.weather.service.util.Period;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PeriodLocalDateValidatorTest {

    private final PeriodLocalDateValidator validator = new PeriodLocalDateValidator();

    @Test
    void shouldReturnTrueWhenPeriodIsNull() {
        assertTrue(validator.isValid(null, null));
    }

    @ParameterizedTest
    @MethodSource("oneLocalDate")
    void shouldReturnTrueWhenFromIsNull(LocalDate to) {
        Period<LocalDate> period = createPeriod(null, to);
        assertTrue(validator.isValid(period, null));
    }

    @ParameterizedTest
    @MethodSource("oneLocalDate")
    void shouldReturnTrueWhenToIsNull(LocalDate from) {
        Period<LocalDate> period = createPeriod(from, null);
        assertTrue(validator.isValid(period, null));
    }

    @ParameterizedTest
    @MethodSource("twoLocalDates")
    void shouldReturnTrueWhenFromBeforeTo(LocalDate from, LocalDate to) {
        Period<LocalDate> period = createPeriod(from, to);
        assertTrue(validator.isValid(period, null));
    }

    @ParameterizedTest
    @MethodSource("twoLocalDates")
    void shouldReturnFalseWhenFromIsAfterTo(LocalDate to, LocalDate from) {
        Period<LocalDate> period = createPeriod(from, to);
        assertFalse(validator.isValid(period, null));
    }

    @ParameterizedTest
    @MethodSource("oneLocalDate")
    void shouldReturnTrueWhenFromEqualsTo(LocalDate localDate) {
        Period<LocalDate> period = createPeriod(localDate, localDate);
        assertTrue(validator.isValid(period, null));
    }

    static Stream<Arguments> oneLocalDate() {
        return Stream.of(
                arguments(LocalDate.of(2020, 2, 5)),
                arguments(LocalDate.of(2030, 5, 17)),
                arguments(LocalDate.now())
        );
    }

    static Stream<Arguments> twoLocalDates() {
        return Stream.of(
                arguments(LocalDate.of(2000, 1, 5), LocalDate.of(2022, 10, 1)),
                arguments(LocalDate.of(2015, 4, 4), LocalDate.of(2050, 6, 6)),
                arguments(LocalDate.of(2040, 4, 4), LocalDate.of(2040, 4, 5)),
                arguments(LocalDate.now().minusDays(1), LocalDate.now())
        );
    }

    private Period<LocalDate> createPeriod(LocalDate from, LocalDate to) {
        return new Period<LocalDate>() {
            @Override
            public LocalDate from() {
                return from;
            }

            @Override
            public LocalDate to() {
                return to;
            }
        };
    }
}
