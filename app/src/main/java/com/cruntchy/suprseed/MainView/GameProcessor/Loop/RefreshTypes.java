package com.cruntchy.suprseed.MainView.GameProcessor.Loop;

public enum RefreshTypes {

    SIXTY_FPS(60),
    ONE_TWENTY_FPS(120);

    private RefreshTypes(int hertz){

        this.hertz = hertz;
    }

    private int hertz;

    public int getHertz(){
        return hertz;
    }

    @Override
    public String toString() {
        return "Current refresh rate: " + hertz + "hz";
    }
}
