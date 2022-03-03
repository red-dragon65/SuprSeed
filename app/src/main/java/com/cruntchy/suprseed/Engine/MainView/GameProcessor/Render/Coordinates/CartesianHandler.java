package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates;

public class CartesianHandler implements LocationHandler {


    private final boolean isPortrait;
    private final boolean isTopLeftOrigin;


    // Constructor
    public CartesianHandler(boolean isPortrait, boolean isTopLeftOrigin) {

        this.isPortrait = isPortrait;
        this.isTopLeftOrigin = isTopLeftOrigin;
    }



    @Override
    public float[] parseLocation(float[] loc){

        if(!isPortrait){

            // Flip x and y values
            float temp = loc[0];
            loc[0] = loc[1];
            loc[1] = temp;

            // Invert x to match expected velocity values
            loc[0] *= -1;


            // Set top left origin for horizontal canvas
            if(isTopLeftOrigin){

                loc[1] += getCanvasWidth();
            }
        }


        // Set center origin if necessary
        if(!isTopLeftOrigin){

            loc = canvasCenter(loc);
        }


        return loc;
    }


    // Returns the coordinates for the center of the canvas
    @Override
    public float[] canvasCenter(float[] loc){

        // Offset x to center
        float middle_x = this.getCanvasWidth() / 2;
        loc[0] += middle_x;

        // Offset y to center
        float middle_y = this.getCanvasHeight() / 2;
        loc[1] -= middle_y;

        return loc;
    }





    // Getters
    @Override
    public boolean isPortrait(){
        return isPortrait;
    }

    @Override
    public boolean isTopLeftOrigin(){
        return  isTopLeftOrigin;
    }


    @Override
    public float getCanvasWidth(){

        /*
        if(isPortrait){

            return canvasWidth;
        }else{

            return canvasHeight;

        }

         */

        return 0;
    }


    @Override
    public float getCanvasHeight(){

         /*
        if(isPortrait){

            return canvasHeight;
        }else{

            return canvasWidth;

        }
         */

        return 0;
    }

}
