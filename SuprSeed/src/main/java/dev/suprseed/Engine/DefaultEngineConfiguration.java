package dev.suprseed.Engine;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.MainView.EngineSettings.ViewConfig;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.EngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.Engine.Lib.Collisions.CollisionDiagnosticsOverlay;
import dev.suprseed.Engine.Lib.Input.TouchInput.InputManager;
import dev.suprseed.Engine.Lib.Input.TouchInput.TouchHandler;

public class DefaultEngineConfiguration extends BaseEngineConfigurator {

    private final EngineConfigurator engineConfigurator;


    public DefaultEngineConfiguration(Context context, RootScene clientScene) {
        super(context);

        // User can build the engine configuration here
        engineConfigurator = new EngineConfigurator(this, clientScene, InputManager.getInstance(), CollisionDiagnosticsOverlay.getInstance())
                .setViewConfig(new ViewConfig(true, true, true, false));

        // Add the input handlers here
        InputManager.getInstance().processorRegister.registerObject(new TouchHandler());
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
