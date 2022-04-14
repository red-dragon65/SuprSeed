package com.cruntchy.suprseed.Engine.Images;

import android.content.Context;
import android.graphics.Typeface;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.CanvasData;

public class FontHolder implements FontRetriever<String>{

    private final Typeface loadedFont;
    private final float fontSize;
    private final String fontId;


    // Constructor
    public FontHolder(int fontResourceId, String fontId, float fontSize, Context context){

        this.loadedFont = context.getResources().getFont(fontResourceId);

        this.fontSize = CanvasData.getInstance().formatCoordinateToCanvas(fontSize);

        this.fontId = fontId;
    }

    public FontHolder(int fontResourceId, float fontSize, Context context){

        this.loadedFont = context.getResources().getFont(fontResourceId);

        this.fontSize = CanvasData.getInstance().formatCoordinateToCanvas(fontSize);

        this.fontId = "";
    }



    /*
    Getters
     */

    @Override
    public Typeface getFont(){

        return loadedFont;
    }

    @Override
    public float getFontSize(){

        return fontSize;
    }

    @Override
    public String getFontId(){
        return fontId;
    }
}
