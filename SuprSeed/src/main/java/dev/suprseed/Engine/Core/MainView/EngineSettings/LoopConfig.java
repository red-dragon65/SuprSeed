package dev.suprseed.Engine.Core.MainView.EngineSettings;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.GameView;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunnable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.RefreshTypes;

public class LoopConfig extends BaseConfig<LoopRunnable<GameView>> {

    // Enforce only one setting across instance usages
    private static RefreshTypes refreshSpeed;
    private static LogicRates logicRate;

    // Constructor
    public LoopConfig(RefreshTypes refreshSpeed, LogicRates logicRate) {

        LoopConfig.refreshSpeed = refreshSpeed;
        LoopConfig.logicRate = logicRate;

        settings.add(new Configurable<LoopRunnable<GameView>>() {
            @Override
            public void applySettings(LoopRunnable<GameView> inputObject) {

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
