package com.leaning.userApp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author rajatha.kunj
 */
public class SpecialCharValidatorImpl implements ConstraintValidator<SpecialCharValidator, String> {

    boolean checkSpecialChar = true;
    String regex = null;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!checkSpecialChar) {
            return true;
        } else if(value == null) {
            return true;
        } else {
            return Pattern.matches(regex, value);
        }
    }

    @Override
    public void initialize(SpecialCharValidator constraintAnnotation) {
        regex = constraintAnnotation.regexp();
        checkSpecialChar = Boolean.getBoolean("true");
    }
}