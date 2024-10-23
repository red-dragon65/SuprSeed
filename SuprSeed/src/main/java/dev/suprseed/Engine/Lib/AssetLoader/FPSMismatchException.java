package dev.suprseed.Engine.Lib.AssetLoader;

public class FPSMismatchException extends Exception {

    public FPSMismatchException() {
        super();
    }

    public FPSMismatchException(String message) {
        super(message);
    }

    public FPSMismatchException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
