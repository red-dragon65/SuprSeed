package dev.suprseed.Engine.Core.MainView.GameViewBuilder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseEngineConfigurator {

    protected Context context;


    // Constructor
    public BaseEngineConfigurator(Context context) {

        this.context = context;
    }


    public abstract View buildView();

    public abstract void setWindowConfig(AppCompatActivity mainActivity);

}
