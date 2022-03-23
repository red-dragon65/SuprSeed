package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop;

public enum RefreshTypes {

    SIXTY_FPS(60);

    /*
    TODO: Revert when refresh rate can be reliably set!
    SIXTY_FPS(60),
    ONE_TWENTY_FPS(120);
     */

    private final int hertz;

    RefreshTypes(int hertz) {

        this.hertz = hertz;
    }

    public int getHertz() {
        return hertz;
    }

    @Override
    public String toString() {
        return "Current refresh rate: " + hertz + "hz";
    }
}
