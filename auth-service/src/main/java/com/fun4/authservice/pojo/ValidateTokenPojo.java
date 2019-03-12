package com.fun4.authservice.pojo;

public class ValidateTokenPojo {
    private boolean valid;

    public ValidateTokenPojo(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
