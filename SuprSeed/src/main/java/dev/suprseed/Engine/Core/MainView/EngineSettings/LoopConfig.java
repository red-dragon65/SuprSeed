package dev.suprseed.Engine.Core.MainView.EngineSettings;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;

/**
 * Allows the user to specify the target refresh rate and logic rate.
 * The LoopManager passes itself into the LoopConfig in order to get its settings set.
 */
public class LoopConfig {

    private int minLogicRate;
    private float logicScaleMultiple;

    public LoopConfig(int minLogicRate, float logicScaleMultiple) {
        this.minLogicRate = minLogicRate;
        this.logicScaleMultiple = logicScaleMultiple;
    }

    public LoopConfig(LogicRates minLogicRate, float logicScaleMultiple) {
        this.minLogicRate = minLogicRate.getTickRate();
        this.logicScaleMultiple = logicScaleMultiple;
    }

    public int getMinLogicRate() {
        return minLogicRate;
    }

    public void setMinLogicRate(int minLogicRate) {
        this.minLogicRate = minLogicRate;
    }

    public void setMinLogicRate(LogicRates minLogicRate) {
        this.minLogicRate = minLogicRate.getTickRate();
    }

    public float getLogicScaleMultiple() {
        return logicScaleMultiple;
    }

    public void setLogicScaleMultiple(int logicScaleMultiple) {
        this.logicScaleMultiple = logicScaleMultiple;
    }

    @Override
    public String toString() {
        return "LoopConfig{" +
                ", logicRate=" + minLogicRate +
                ", logicScaleMultiple=" + logicScaleMultiple +
                '}';
    }
}
