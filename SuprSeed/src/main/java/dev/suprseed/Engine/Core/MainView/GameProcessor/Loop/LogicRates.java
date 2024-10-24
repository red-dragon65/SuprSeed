package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

public enum LogicRates {

    THIRTY_TICKS(30),
    SIXTY_TICKS(60),
    ONE_TWENTY_TICKS(120),
    TWO_FORTY_TICKS(240);

    private final int tickRate;

    LogicRates(int tickRate) {

        this.tickRate = tickRate;
    }

    public int getTickRate() {
        return tickRate;
    }

    @Override
    public String toString() {
        return tickRate + " tps";
    }
}
