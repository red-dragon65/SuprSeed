package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;


/**
 * Used by LoopController in order to pass tick data config to the LoopRunner.
 */
public class LoopTickRateMultiples {

    private int refreshMultiple = 0;
    private int logicMultiple = 0;

    public LoopTickRateMultiples() {

    }

    public LoopTickRateMultiples(int refreshMultiple, int logicMultiple) {
        this.refreshMultiple = refreshMultiple;
        this.logicMultiple = logicMultiple;
    }

    public int getRefreshMultiple() {
        return refreshMultiple;
    }

    public void setRefreshMultiple(int refreshMultiple) {
        this.refreshMultiple = refreshMultiple;
    }

    public int getLogicMultiple() {
        return logicMultiple;
    }

    public void setLogicMultiple(int logicMultiple) {
        this.logicMultiple = logicMultiple;
    }
}
