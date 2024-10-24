package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

public class InvalidTagException extends Exception {
    public InvalidTagException() {
        super();
    }

    public InvalidTagException(String message) {
        super(message);
    }

    public InvalidTagException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
