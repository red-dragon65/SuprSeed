package com.cruntchy.suprseed.Engine.Core.MainView.EngineSettings;

import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Loop.RefreshTypes;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Loop.RunnableConfig;

public class LoopConfig extends BaseConfig<RunnableConfig<GameView>> {

    // Enforce only one setting across instance usages
    private static RefreshTypes refreshSpeed;
    private static LogicRates logicRate;

    // Constructor
    public LoopConfig(RefreshTypes refreshSpeed, LogicRates logicRate) {

        LoopConfig.refreshSpeed = refreshSpeed;
        LoopConfig.logicRate = logicRate;

        settings.add(new Configurable<RunnableConfig<GameView>>() {
            @Override
            public void applySettings(RunnableConfig<GameView> inputObject) {

                inputObject.setRefreshSpeed(LoopConfig.refreshSpeed);
                inputObject.setLogicRate(LoopConfig.logicRate);
            }

            @Override
            public boolean active() {
                return true;
            }

            @Override
            public String getId() {
                return "loopRates";
            }
        });
    }
}
