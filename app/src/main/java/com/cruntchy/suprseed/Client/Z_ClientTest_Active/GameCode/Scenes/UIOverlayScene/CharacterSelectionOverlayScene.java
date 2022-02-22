package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.UIOverlayScene;

import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;

public class CharacterSelectionOverlayScene  extends Scene {

    private AssetLoader assets;

    // Constructor
    public CharacterSelectionOverlayScene(SceneController sceneManager, String id, AssetLoader assets){
        super(sceneManager, id);

        this.assets = assets;
    }

    @Override
    public void initStartingState() {

    }

    @Override
    public void logicLoop() {

    }

    @Override
    public void drawingLoop() {

    }
}
