package com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.CanvasData;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.StartingState;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;

import java.util.List;
import java.util.Random;

public class ObstacleSpawnerComponent implements Logic, StartingState {

    private List<Sprite> obstacles;
    private List<ImageHandler> obstacleImages;

    private Random rand;

    private final int yGenerationDistance = 3000;



    public ObstacleSpawnerComponent(List<Sprite> obstacles, List<ImageHandler> obstacleImages){

        this.obstacles = obstacles;
        this.obstacleImages = obstacleImages;
        rand = new Random();
    }


    @Override
    public void runLogic() {

        /*
        Find non-active sprite
        Generate new position at the bottom of the screen

         */

        for(Sprite s : obstacles){

            if(!s.isActive()){

                activateSprite(s);

                setLocation(s);

                setImage(s);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setStartingState() {

        for(Sprite s : obstacles){
            setLocation(s);
        }
    }


    private void activateSprite(Sprite s){

        // Activate sprite
        s.setActive(true);
        s.setDrawable(true);
    }

    private void setLocation(Sprite s){

        // Set location to random value below screen
        s.setX(rand.nextInt((int) (CanvasData.getInstance().getScaledWidth() - s.getImageHandler().getSelectedImageSet().getScaledWidth())));

        float height = rand.nextInt(yGenerationDistance);
        height += CanvasData.getInstance().getScaledHeight();

        s.setY(height);
    }

    private void setImage(Sprite s){

        // Get random image
        int select = rand.nextInt(obstacleImages.size());

        s.setImageHandler(obstacleImages.get(select));
    }
}