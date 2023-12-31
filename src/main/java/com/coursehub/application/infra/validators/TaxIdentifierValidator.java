package com.coursehub.application.infra.validators;

import com.coursehub.application.infra.annotation.TaxIdentifier;
import jakarta.validation.ConstraintValidator;

public class TaxIdentifierValidator implements ConstraintValidator<TaxIdentifier, String> {
    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        if (value.length() > 11)
            return false;

        while (value.length() != 11)
            value = '0' + value;

        boolean igual = true;
        for (int i = 1; i < 11 && igual; i++)
            if (value.charAt(i) != value.charAt(0))
                igual = false;

        if (igual || value.equals("12345678909"))
            return false;

        int[] numeros = new int[11];

        for (int i = 0; i < 11; i++)
            numeros[i] = Integer.parseInt(String.valueOf(value.charAt(i)));

        int soma = 0;
        for (int i = 0; i < 9; i++)
            soma += (10 - i) * numeros[i];

        int resultado = soma % 11;

        if (resultado == 1 || resultado == 0) {
            if (numeros[9] != 0)
                return false;
        } else if (numeros[9] != 11 - resultado)
            return false;

        soma = 0;
        for (int i = 0; i < 10; i++)
            soma += (11 - i) * numeros[i];

        resultado = soma % 11;

        if (resultado == 1 || resultado == 0) {
            if (numeros[10] != 0)
                return false;
        } else if (numeros[10] != 11 - resultado)
            return false;

        return true;
    }
}
