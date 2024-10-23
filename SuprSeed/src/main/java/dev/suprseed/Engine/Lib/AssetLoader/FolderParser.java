package dev.suprseed.Engine.Lib.AssetLoader;

import java.io.IOException;

public interface FolderParser {

    String[] getSubPaths(String basePath) throws IOException;

}
