package com.cruntchy.suprseed.Client.Z_ClientGame.GameCode;

import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.Images.ImageCollection;
import com.cruntchy.suprseed.Engine.Images.ImageCollectionAnimator;
import com.cruntchy.suprseed.Engine.Images.ImageSingle;

public class AssetScript extends AssetLoader {


    public AssetScript(Streamable assetStreamer, FolderParser folderParser){
        super(assetStreamer, folderParser);
    }


    @Override
    public void loadAssets() {

        // Client can load sprite images here
    }

}
