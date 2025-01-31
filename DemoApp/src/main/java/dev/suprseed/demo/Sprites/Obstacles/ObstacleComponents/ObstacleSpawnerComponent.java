package dev.suprseed.demo.Sprites.Obstacles.ObstacleComponents;

import java.util.List;
import java.util.Random;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.ViewPort;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class ObstacleSpawnerComponent implements ResetableComponent {

    private final List<Sprite> obstacles;
    private final List<AssetBundle> obstacleImages;

    private final Random rand;

    private final int yGenerationDistance = 3000;


    public ObstacleSpawnerComponent(List<Sprite> obstacles, List<AssetBundle> obstacleImages) {

        this.obstacles = obstacles;
        this.obstacleImages = obstacleImages;
        rand = new Random();
    }


    @Override
    public void update() {

        /*
        Find non-active sprite
        Generate new position at the bottom of the screen

         */

        for (Sprite s : obstacles) {

            if (!s.isActive()) {

                activateSprite(s);

                setLocation(s);

                setImage(s);
            }
        }
    }

    @Override
    public void resetState() {

        for (Sprite s : obstacles) {
            setLocation(s);
        }
    }


    private void activateSprite(Sprite s) {

        // Activate sprite
        s.setActive(true);
        s.setDrawable(true);
    }

    private void setLocation(Sprite s) {

        // Set location to random value below screen
        s.setX(rand.nextInt((int) (ViewPort.getInstance().getWidth() - s.getWidth())));

        float height = rand.nextInt(yGenerationDistance);
        height += ViewPort.getInstance().getHeight();

        s.setY(height);
    }

    private void setImage(Sprite s) {

        // Get random image
        int select = rand.nextInt(obstacleImages.size());

        s.setImageHandler(obstacleImages.get(select));
    }
}
