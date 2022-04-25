package com.cruntchy.suprseed.Client;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput.InputManager;
import com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.Engine.Core.MainView.EngineSettings.ViewConfig;
import com.cruntchy.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import com.cruntchy.suprseed.Engine.Core.MainView.GameViewBuilder.EngineConfigurator;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;

public class ClientEngineConfigurator extends BaseEngineConfigurator {

    private final EngineConfigurator engineConfigurator;

    public ClientEngineConfigurator(Context context) {
        super(context);

        // User can specify their root scene here
        RootScene clientSpecifiedRootScene = new MainScene(context);

        // User can build the engine configuration here
        engineConfigurator = new EngineConfigurator(this, clientSpecifiedRootScene)
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
