package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.HomeScreen;

import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;

public class LandingScene extends Scene {

    private final AssetLoader assets;

    private Scene charScene;


    // Constructor
    public LandingScene(SceneController sceneManager, String id, AssetLoader assets){
        super(sceneManager, id);

        this.assets = assets;



        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene



        /*
        Sprite background = new sprite();
        Sprite characters = new sprite();
         */
    }


    @Override
    public void runLogic() {

    }
}
