package com.cruntchy.suprseed.Z_ClientGame;

import com.cruntchy.suprseed.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.AssetLoader.FolderParser;
import com.cruntchy.suprseed.AssetLoader.Streamable;
import com.cruntchy.suprseed.Images.ImageCollection;
import com.cruntchy.suprseed.Images.ImageCollectionAnimator;
import com.cruntchy.suprseed.Images.ImageSingle;

public class AssetScript extends AssetLoader {


    public AssetScript(Streamable assetStreamer, FolderParser folderParser){
        super(assetStreamer, folderParser);
    }


    @Override
    public void loadAssets() {

        // CLIENT CODE GOES HERE!!!


        ImageSingle hero = new ImageSingle("path/to/image.png", 1, assetStreamer);

        ImageCollection enemy = new ImageCollection("path/to/folder", 1, assetStreamer, folderParser);

        ImageCollectionAnimator bullets = new ImageCollectionAnimator("some/folder/path", 1, assetStreamer, folderParser, 20, true);
    }

}
