package com.oren.urlshortener.advice;

public class ErrorDetails {
    private String error;
    private String description;

    public ErrorDetails(String error, String description) {
        this.error = error;
        this.description = description;
    }

    public ErrorDetails() {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorDetails{");
        sb.append("error='").append(error).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
