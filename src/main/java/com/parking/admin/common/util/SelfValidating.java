package com.parking.admin.common.util;

import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidating<T> {

    private final Validator validator;

    public SelfValidating() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}