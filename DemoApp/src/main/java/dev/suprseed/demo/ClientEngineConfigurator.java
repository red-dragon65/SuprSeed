package dev.suprseed.demo;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.MainView.EngineSettings.LoopConfig;
import dev.suprseed.Engine.Core.MainView.EngineSettings.ViewConfig;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LogicRates;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.SceneStarter;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.EngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchEventDispatcher;
import dev.suprseed.demo.Subscenes.GameDemoMainScene;


/*
You can set your custom engine configuration here as needed
 */
public class ClientEngineConfigurator extends BaseEngineConfigurator {

    private final EngineConfigurator engineConfigurator;

    public ClientEngineConfigurator(Context context, SceneStarter sceneStarter) {
        super(context, sceneStarter);

        // User can build the engine configuration here
        engineConfigurator = new EngineConfigurator(context, sceneStarter)
                .setViewConfig(new ViewConfig(true, true, true, false))
                .setLoopConfig(new LoopConfig(LogicRates.ONE_TWENTY_TICKS, 0.2f));

        // Add the input handlers here
        EngineTools.getInputManager().getDispatcherRegistry().registerObject(new TouchEventDispatcher());
    }

    @Override
    public View buildView() {

        // Return the engine configurators built view
        return engineConfigurator.buildView();
    }

    @Override
    public void setWindowConfig(AppCompatActivity mainActivity) {

        // Let the configurators default 'ViewConfig' instance handle the mainActivity preferences
        engineConfigurator.setWindowConfig(mainActivity);
    }
}
