package com.example.BlockPuzzle.core.exeptions;

public class InvalidPlacementException extends RuntimeException {
    public InvalidPlacementException(String message) {
        super(message);
    }
}