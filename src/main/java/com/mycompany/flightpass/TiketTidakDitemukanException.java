package com.mycompany.flightpass;

// Turunan dari class Exception
public class TiketTidakDitemukanException extends Exception {
    public TiketTidakDitemukanException(String message) {
        super(message);
    }
}