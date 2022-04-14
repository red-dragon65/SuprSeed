package com.cruntchy.suprseed.Client;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GatewayScene;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.ViewConfig;
import com.cruntchy.suprseed.Engine.MainView.GameViewBuilder.BaseEngineConfigurator;
import com.cruntchy.suprseed.Engine.MainView.GameViewBuilder.EngineConfigurator;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.RootScene;

public class ClientEngineConfigurator extends BaseEngineConfigurator {

    private final EngineConfigurator engineConfigurator;

    public ClientEngineConfigurator(Context context, Resources res, SharedPreferences gameData) {
        super(context, res, gameData);

        // User can specify their root scene here
        RootScene clientSpecifiedRootScene = new GatewayScene();

        // User can build the engine configuration here
        engineConfigurator = new EngineConfigurator(this, clientSpecifiedRootScene)
                .setViewConfig(new ViewConfig(true, true, true, false));
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
