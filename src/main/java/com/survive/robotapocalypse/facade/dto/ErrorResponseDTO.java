package com.survive.robotapocalypse.facade.dto;

public class ErrorResponseDTO {

    private String message;
    private String warning;

    public ErrorResponseDTO(String message, String warning) {
        this.message = message;
        this.warning = warning;
    }

    public ErrorResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
