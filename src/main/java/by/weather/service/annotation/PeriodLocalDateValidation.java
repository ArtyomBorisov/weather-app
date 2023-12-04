package by.weather.service.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PeriodLocalDateValidator.class)
public @interface PeriodLocalDateValidation {
    String message() default "Field 'to' should be later than or equal to 'from'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
