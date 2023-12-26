package com.coursehub.application.infra.annotation;

import com.coursehub.application.infra.Validators.NotBlankIfNotNullValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NotBlankIfNotNullValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface NotBlankIfNotNull {
    String message() default "Se o valor estiver presente, não pode ser vazio ou composto apenas por espaços em branco.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
