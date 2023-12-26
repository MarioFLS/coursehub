package com.coursehub.application.infra.Validators;

import com.coursehub.application.infra.annotation.NotBlankIfNotNull;
import jakarta.validation.ConstraintValidator;

public class NotBlankIfNotNullValidator implements ConstraintValidator<NotBlankIfNotNull, String> {
    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !value.isBlank();
    }
}
