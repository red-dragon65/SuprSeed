package dev.suprseed.demo.Client;

import android.content.Context;

import dev.suprseed.demo.Client.DemoScene.Subscenes.GameDemoMainScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;

public class MainScene extends RootScene {

    public MainScene(Context context) {
        super(context);
    }

    @Override
    public void initStartingState(Context context) {

        // Use this to get the game save data
        //context.getSharedPreferences("GAME_DATA", MODE_PRIVATE);

        // Use this to get the asset resources
        //context.getResources();

        // Starts the scene 1 example package
        BaseScene startingScene = new GameDemoMainScene(this, "TopScene");
    }
}
