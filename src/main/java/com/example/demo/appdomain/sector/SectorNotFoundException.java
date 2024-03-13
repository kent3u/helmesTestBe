package com.example.demo.appdomain.sector;

public class SectorNotFoundException extends RuntimeException {

    public SectorNotFoundException() {
        super("No sector found for given id");
    }
}
