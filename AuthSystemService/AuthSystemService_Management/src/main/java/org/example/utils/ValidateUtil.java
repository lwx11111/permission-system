package org.example.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

import java.util.Set;

/**
 * @Author 刘文轩
 * @Date 2024/8/20 9:08
 */
public class ValidateUtil {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验实体类
     */
    public static <T> void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(t);
        if (constraintViolations.size() > 0) {
            StringBuilder validateError = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                validateError.append(constraintViolation.getMessage());
                break;
            }

            throw new ValidationException(validateError.toString());
        }
    }
}
