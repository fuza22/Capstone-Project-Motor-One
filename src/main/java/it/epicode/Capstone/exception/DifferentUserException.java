package it.epicode.Capstone.exception;

public class DifferentUserException extends RuntimeException{

    public DifferentUserException(String message) {
        super(message);
    }
}
