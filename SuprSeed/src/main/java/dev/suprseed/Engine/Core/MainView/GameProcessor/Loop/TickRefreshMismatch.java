package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

public class TickRefreshMismatch extends RuntimeException {

    public TickRefreshMismatch() {
        super();
    }

    public TickRefreshMismatch(String message) {
        super(message);
    }

    public TickRefreshMismatch(String message, Throwable throwable) {
        super(message, throwable);
    }
}
