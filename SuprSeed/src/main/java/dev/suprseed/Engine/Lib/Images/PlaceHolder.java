package dev.suprseed.Engine.Lib.Images;

import androidx.annotation.NonNull;

import java.io.IOException;

import dev.suprseed.Engine.Lib.AssetLoader.Streamable;

public class PlaceHolder extends BitmapSingle {

    public PlaceHolder(String filePath, float imageScale, Streamable imageStreamer) throws IOException {
        super(filePath, imageScale, imageStreamer, TAG.PLACEHOLDER.toString());
    }

    public enum TAG {

        PLACEHOLDER("placeHolder");

        String tag;

        TAG(String tag) {
            this.tag = tag;
        }

        @NonNull
        public String toString() {
            return tag;
        }
    }
}
