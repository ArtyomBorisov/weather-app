package by.weather.service.annotation;

import by.weather.service.util.Period;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PeriodLocalDateValidator implements ConstraintValidator<PeriodLocalDateValidation, Period<LocalDate>> {
    @Override
    public boolean isValid(Period<LocalDate> period, ConstraintValidatorContext context) {
        if(period == null) return true;

        LocalDate from = period.from();
        LocalDate to = period.to();

        return from == null || to == null || from.isBefore(to) || from.isEqual(to);
    }
}
