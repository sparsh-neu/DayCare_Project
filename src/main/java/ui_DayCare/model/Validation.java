package ui_DayCare.model;

public class Validation {


    private String message;
    private boolean isValid;

    public Validation(String message, boolean isValid) {
        this.isValid = isValid;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return isValid;
    }
}
