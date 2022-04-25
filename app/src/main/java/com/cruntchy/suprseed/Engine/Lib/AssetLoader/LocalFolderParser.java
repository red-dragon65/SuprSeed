package com.cruntchy.suprseed.Engine.Lib.AssetLoader;

import android.content.res.Resources;

import java.io.IOException;

public class LocalFolderParser implements FolderParser {

    private final Resources res;


    // Constructor
    public LocalFolderParser(Resources res){

        this.res = res;
    }

    @Override
    public String[] getSubPaths(String basePath) {

        // Hold full image path for each file in folder
        String[] imagePaths;

        // Try and load the file paths
        try {

            // Get file names in folder
            imagePaths = res.getAssets().list(basePath);

            // Add base path to file name
            for(int i = 0; i < imagePaths.length; i++){

                imagePaths[i] = basePath + "/" + imagePaths[i];
            }

        } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException();
        }

        // Return the file paths to the caller
        return imagePaths;
    }
}
