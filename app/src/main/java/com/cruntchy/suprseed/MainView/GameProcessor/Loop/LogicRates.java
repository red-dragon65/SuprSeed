package com.cruntchy.suprseed.MainView.GameProcessor.Loop;

public enum LogicRates {

    THIRTY_TICKS(30),
    SIXTY_TICKS(60),
    ONE_TWENTY_TICKS(120),
    TWO_FORTY_TICKS(240);

    private LogicRates(int tickRate){

        this.tickRate = tickRate;
    }

    private int tickRate;

    public int getTickRate(){
        return tickRate;
    }

    @Override
    public String toString() {
        return "Logic rate: " + tickRate;
    }
}
