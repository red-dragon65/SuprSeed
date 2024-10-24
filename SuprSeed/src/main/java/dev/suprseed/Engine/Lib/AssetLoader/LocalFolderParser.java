package dev.suprseed.Engine.Lib.AssetLoader;

import android.content.res.Resources;

import java.io.IOException;

public class LocalFolderParser implements FolderParser {

    private final Resources res;


    // Constructor
    public LocalFolderParser(Resources res) {

        this.res = res;
    }

    @Override
    public String[] getSubPaths(String basePath) throws IOException {

        // Hold full image path for each file in folder
        // Try and load the file paths
        // Get file names in folder
        String[] imagePaths = res.getAssets().list(basePath);

        // Add base path to file name
        for (int i = 0; i < imagePaths.length; i++) {

            imagePaths[i] = basePath + "/" + imagePaths[i];
        }

        // Return the file paths to the caller
        return imagePaths;
    }
}
