package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Assets;

import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.Images.ImageCollection;
import com.cruntchy.suprseed.Engine.Images.ImageCollectionAnimator;
import com.cruntchy.suprseed.Engine.Images.ImageSingle;

import java.util.HashMap;

public class AssetScriptTest extends AssetLoader {


    public AssetScriptTest(Streamable assetStreamer, FolderParser folderParser){
        super(assetStreamer, folderParser);
    }


    @Override
    public void loadAssets() {

        // CLIENT CODE GOES HERE!!!

        ImageSingle hero = new ImageSingle("hero_left_jumpman.png", 1, assetStreamer);
        images.put("hero", hero);


        //ImageSingle hero = new ImageSingle("path/to/image.png", 1, assetStreamer);

        //ImageSingle enemies = new ImageSingle("path/to/image.png", 1, assetStreamer);

        //ImageCollection enemy = new ImageCollection("path/to/folder", 1, assetStreamer, folderParser);

        //ImageCollectionAnimator bullets = new ImageCollectionAnimator("some/folder/path", 1, assetStreamer, folderParser, 20, true);
    }

}
