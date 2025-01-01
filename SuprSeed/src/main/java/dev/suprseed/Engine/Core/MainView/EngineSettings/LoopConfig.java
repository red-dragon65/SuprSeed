package dev.suprseed.Engine.Core.MainView.EngineSettings;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.RefreshTypes;

/**
 * Allows the user to specify the target refresh rate and logic rate.
 * The LoopManager passes itself into the LoopConfig in order to get its settings set.
 */
public class LoopConfig {

    // Enforce only one setting across instance usages
    private RefreshTypes refreshSpeed;
    private LogicRates logicRate;
    private float logicScaleMultiple;
    private boolean accurateTickRate = true;

    public LoopConfig(RefreshTypes refreshSpeed, LogicRates logicRate, float logicScaleMultiple, boolean accurateTickRate) {
        this.refreshSpeed = refreshSpeed;
        this.logicRate = logicRate;
        this.logicScaleMultiple = logicScaleMultiple;
        this.accurateTickRate = accurateTickRate;
    }

    public RefreshTypes getRefreshSpeed() {
        return refreshSpeed;
    }

    public void setRefreshSpeed(RefreshTypes refreshSpeed) {
        this.refreshSpeed = refreshSpeed;
    }

    public LogicRates getLogicRate() {
        return logicRate;
    }

    public void setLogicRate(LogicRates logicRate) {
        this.logicRate = logicRate;
    }

    public boolean isAccurateTickRate() {
        return accurateTickRate;
    }

    public void setAccurateTickRate(boolean accurateLogicRate) {
        this.accurateTickRate = accurateLogicRate;
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
                "refreshSpeed=" + refreshSpeed +
                ", logicRate=" + logicRate +
                ", logicScaleMultiple=" + logicScaleMultiple +
                ", accurateTickRate=" + accurateTickRate +
                '}';
    }
}
