package com.cruntchy.suprseed.Client.Z_ClientGame.GameCode;

import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.BaseScene;

public class AssetScript extends AssetLoader {


    public AssetScript(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser){
        super(parentScene, assetStreamer, folderParser);
    }


    @Override
    public void loadAssets(BaseScene parentScene) {

        // Client can load sprite images here
    }

}
