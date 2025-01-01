package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

public enum RefreshTypes {

    SIXTY_FPS(60),
    ONE_TWENTY_FPS(120);

    private final int hertz;

    RefreshTypes(int hertz) {

        this.hertz = hertz;
    }

    public int getHertz() {
        return hertz;
    }

    @Override
    public String toString() {
        return hertz + "hz";
    }
}
