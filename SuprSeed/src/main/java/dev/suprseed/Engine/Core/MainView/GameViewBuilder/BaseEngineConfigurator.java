package dev.suprseed.Engine.Core.MainView.GameViewBuilder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.SceneStarter;

public abstract class BaseEngineConfigurator {

    protected Context context;


    // Constructor
    public BaseEngineConfigurator(Context context, SceneStarter sceneStarter) {

        this.context = context;
    }


    public abstract View buildView();

    public abstract void setWindowConfig(AppCompatActivity mainActivity);

}
