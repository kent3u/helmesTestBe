package com.example.demo.appdomain.involvementapplication;

public class NoInvolvementApplicationForClientException extends RuntimeException {

    public NoInvolvementApplicationForClientException() {
        super("No involvement application found for client");
    }
}
