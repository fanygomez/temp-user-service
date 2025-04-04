package com.bci.user_service.components.validator.impl;

import com.bci.user_service.components.validator.ValidPasswordFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ValidatorPasswordFormat implements ConstraintValidator<ValidPasswordFormat, String> {
    @Value("${config.regex.password}")
    private String passwordRegex;
    private Pattern pattern;
    @Override
    public void initialize(ValidPasswordFormat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        pattern = Pattern.compile(passwordRegex);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }
}
