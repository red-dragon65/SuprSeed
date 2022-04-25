package com.cruntchy.suprseed.Client;

import android.content.Context;

import com.cruntchy.suprseed.Client.Scene1.Subscenes.TopScene;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;

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
        BaseScene startingScene = new TopScene(this, "TopScene");
    }
}
