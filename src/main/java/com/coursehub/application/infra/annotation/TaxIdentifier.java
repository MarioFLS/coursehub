package com.coursehub.application.infra.annotation;

import com.coursehub.application.infra.validators.TaxIdentifierValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TaxIdentifierValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface TaxIdentifier {
    String message() default "Você precisa informar um CPF ou CNPJ válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
