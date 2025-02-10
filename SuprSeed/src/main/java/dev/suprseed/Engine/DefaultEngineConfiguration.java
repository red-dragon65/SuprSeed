package dev.suprseed.Engine;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.MainView.EngineSettings.ViewConfig;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.SceneStarter;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.EngineConfigurator;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchEventDispatcher;

public class DefaultEngineConfiguration extends BaseEngineConfigurator {

    private final EngineConfigurator engineConfigurator;

    public DefaultEngineConfiguration(Context context, SceneStarter sceneStarter) {
        super(context);

        // User can build the engine configuration here
        engineConfigurator = new EngineConfigurator(context, sceneStarter)
                .setViewConfig(new ViewConfig(true, true, true, false));

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
