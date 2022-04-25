package com.cruntchy.suprseed.Engine.Lib.AssetLoader;

public class ImageProcessor implements ImageTransformer {



    /*
    public void init(int width, int height, String fileName, Streamable assetStreamer){

        this.scaledCanvasWidth = width;
        this.scaledCanvasWidth = height;

        Bitmap bg = assetStreamer.loadImage_Unscaled(fileName);

        this.spriteImageScaleRatio = (float) width / bg.getWidth();
    }*/


    public void rotateSprite(/*Sprite sprite,*/ float degrees){


        // TODO: Figure out if canvas is needed for rotations


        // TODO: Figure out how to rotate sprites on an individual basis

        // TODO: Call this from 'AssetLoader'
        //  The asset loader should automatically rotate all images
        //  if the canvas orientation has been set to landscape!

    }


    public void flipSprite(/*Sprite sprite*/ boolean vertical){

    }

    public void invertColors(/*Sprite sprite*/){

    }

}
