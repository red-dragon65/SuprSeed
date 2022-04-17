package com.cruntchy.suprseed.Client;

import android.content.Context;

import com.cruntchy.suprseed.Client.Scene1.Subscenes.TopScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.RootScene;

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

        /*
        -> Read in assets
        -> Instantiate dependencies

        -> Create game objects OR initialize game sub scenes
         */


        // Starts the scene 1 example package
        BaseScene startingScene = new TopScene(this, "TopScene");
    }
}
