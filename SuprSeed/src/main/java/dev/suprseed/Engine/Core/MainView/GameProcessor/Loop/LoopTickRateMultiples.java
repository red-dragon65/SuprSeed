package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;


/**
 * Used by LoopController in order to pass tick data config to the LoopRunner.
 */
public class LoopTickRateMultiples {

    private int logicMultiple = 1;

    public LoopTickRateMultiples() {

    }

    public LoopTickRateMultiples(int logicMultiple) {
        this.logicMultiple = logicMultiple;
    }

    public int getLogicMultiple() {
        return logicMultiple;
    }

    public void setLogicMultiple(int logicMultiple) {

        if(logicMultiple <= 0){
            throw new IndexOutOfBoundsException("The logic multiple must be at least 1 or greater! The value given was: " + logicMultiple);
        }

        this.logicMultiple = logicMultiple;
    }
}
