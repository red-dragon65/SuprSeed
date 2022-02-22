package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.HomeScreen;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.UIOverlayScene.CharacterSelectionOverlayScene;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;

public class LandingScene extends Scene {

    private AssetLoader assets;

    private Scene charScene;


    // Constructor
    public LandingScene(SceneController sceneManager, String id, AssetLoader assets){
        super(sceneManager, id);

        this.assets = assets;
    }


    @Override
    public void initStartingState() {

        // Load any third party game function

        // Read in assets

        // Create game objects

        // --------------

        // OR, initialize a scene

        charScene = new CharacterSelectionOverlayScene(sceneManager, "characterSelection", assets);


        /*
        Sprite background = new sprite();
        Sprite characters = new sprite();
         */
    }

    @Override
    public void logicLoop() {

        // Call run???

        charScene.logicLoop();

        /*

        background.logic();
        characters.logic();


        if(tap occurs){

            this.active = false;
            charScene.active = false;

            sceneManager.changeScene("FallingScene");
        }
         */

    }

    @Override
    public void drawingLoop() {

        // Call draw???

        charScene.drawingLoop();

        /*
        background.drawing();
        characters.drawing();
         */
    }
}
