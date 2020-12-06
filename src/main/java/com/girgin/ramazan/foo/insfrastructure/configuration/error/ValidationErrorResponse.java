package com.girgin.ramazan.foo.insfrastructure.configuration.error;

import java.util.Collections;
import java.util.List;

public class ValidationErrorResponse {
    private final List<Violation> violations;

    public ValidationErrorResponse(List<Violation> violations) {
        this.violations = Collections.unmodifiableList(violations);
    }

    public List<Violation> getViolations() {
        return violations;
    }
}
