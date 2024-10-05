package dev.suprseed.demo;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.DefaultEngineConfiguration;
import dev.suprseed.Engine.EngineActivity;

public class MainActivity extends EngineActivity {

    @Override
    protected int loadView() {
        return R.layout.activity_main;
    }

    @Override
    protected ConstraintLayout loadViewLayout() {
        return findViewById(R.id.game_layout);
    }

    @Override
    protected BaseEngineConfigurator loadConfig(Context context) {
        return new DefaultEngineConfiguration(context, new MainScene(context));

        // You can specify your custom engine configuration like this
        //return new ClientEngineConfigurator(context);
    }
}
