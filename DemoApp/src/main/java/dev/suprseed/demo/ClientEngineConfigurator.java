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
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchEventDispatcher;
import dev.suprseed.demo.Subscenes.GameDemoMainScene;


/*
You can set your custom engine configuration here as needed
 */
public class ClientEngineConfigurator extends BaseEngineConfigurator {

    private final EngineConfigurator engineConfigurator;

    public ClientEngineConfigurator(Context context) {
        super(context);

        // User can specify their main scene here
        SceneStarter clientSceneStarter = rootScene -> new GameDemoMainScene(rootScene, "TopScene");

        /*SceneStarter clientSc = new SceneStarter() {
            @Override
            public void initStartingState(RootScene rootScene) {

                new GameDemoMainScene(rootScene, "TopScene");
            }
        };*/

        // User can build the engine configuration here
        engineConfigurator = new EngineConfigurator(context, clientSceneStarter)
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
