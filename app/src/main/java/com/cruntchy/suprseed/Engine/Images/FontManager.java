package com.cruntchy.suprseed.Engine.Images;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Map;

public abstract class FontManager {

    // TODO: Finish this
    //  This allows the client to put all fonts related to a scene / scene group in one spot

    private Map<String, Typeface> loadedFonts;


    // Constructor
    public FontManager(){


    }


    /*
    FontRId
    TypeFace
    Font size * scale value
    String id
     */



    public void loadFonts(Map<String, Integer> fonts, Context context){

        //Typeface someFont = context.getResources().getFont(R.font.something);

        /*
        for(Font f : fonts){

            loadedFonts.key = f.key
            loadedFonts.value = context.getResources().getFont(f.value);
        }
         */
    }


    public Typeface getFont(String fontId){


        /*
        return loadedFonts.find(fontId).value;
         */


        return null;
    }
}
