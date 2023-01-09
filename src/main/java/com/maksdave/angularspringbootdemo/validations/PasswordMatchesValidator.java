package com.maksdave.angularspringbootdemo.validations;

import com.maksdave.angularspringbootdemo.annotations.PasswordMatches;
import com.maksdave.angularspringbootdemo.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SignupRequest userSignupRequest = (SignupRequest) value;
        return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
    }
}
