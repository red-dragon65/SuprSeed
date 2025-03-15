package dev.suprseed.demo.GamePlayScene.EntityScene.Hero.HeroComponents;

import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineTools;

public class WallCollisionComponent implements Component {

    private final Sprite sprite;

    // Constructor
    public WallCollisionComponent(Sprite sprite) {

        this.sprite = sprite;
    }


    @Override
    public void update() {

        // Get the current images scaled dimensions
        float width = sprite.getWidth();
        float height = sprite.getHeight();


        // Prevent hero from moving off left/right side of screen

        // This should see if the sprite moves off the right side of the screen
        if (sprite.getX() > EngineTools.getViewPort().getWidth() - width) {

            sprite.setX(EngineTools.getViewPort().getWidth() - width);

        } else if (sprite.getX() < 0) { // Check if the sprite moves off the left side of the screen

            sprite.setX(0);
        }


        // Prevent sprite from moving off bottom/top of screen

        // Check if sprite moves off bottom of screen
        if (sprite.getY() > EngineTools.getViewPort().getHeight() - height) {

            sprite.setY(EngineTools.getViewPort().getHeight() - height);

        } else if (sprite.getY() < 0) { // Check if sprite moves off top of screen

            sprite.setY(0);
        }
    }
}
