package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

public enum LogicRates {

    /*
    For each given target tick rate that can be chosen, there is a possibility
    that it gets upgraded to a higher tick rate in order to match the screens
    refresh rate.

    NOTE: The provided ticks rates in this enum don't really matter. Any arbitrary tick rate
    will automatically get upgraded as needed in order to match the refresh rate multiple.

    NOTE: Arbitrary tick rates essentially get rounded up to the next tick rate tier. Ie, a tick
    rate of 61 gets treated as if it is a tick rate of 90. A tick rate of 59 gets treated as if
    it were a tick rate of 60.

    Standard refresh rates
    ticks @ 60hz, 90hz, 120hz

    Target tick rate | Actual tick rate
    60 - 60, 90, 120
    max: 120 ticks @ 120 hz

    90 - 120, 90, 120
    max: 120 ticks @ 60/120 hz

    120 - 120, 180, 120
    max: 180 ticks @ 90 hz

    180 - 180, 180, 240
    max: 240 ticks @ 120 hz

    240 - 240, 270, 240
    max: 270 ticks @ 90 hz

    300 - 300, 360, 360
    max: 360 ticks @ 90/120 hz
     */

    SIXTY_TICKS(60),
    NINETY_TICKS(90),
    ONE_TWENTY_TICKS(120),
    ONE_EIGHTY_TICKS(180),
    TWO_FORTY_TICKS(240),
    THREE_HUNDRED_TICKS(300);

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
