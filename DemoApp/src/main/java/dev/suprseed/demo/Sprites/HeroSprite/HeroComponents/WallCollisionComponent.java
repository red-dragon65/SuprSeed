package dev.suprseed.demo.Sprites.HeroSprite.HeroComponents;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.ViewPort;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

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
        if (sprite.getX() > ViewPort.getInstance().getWidth() - width) {

            sprite.setX(ViewPort.getInstance().getWidth() - width);

        } else if (sprite.getX() < 0) { // Check if the sprite moves off the left side of the screen

            sprite.setX(0);
        }


        // Prevent sprite from moving off bottom/top of screen

        // Check if sprite moves off bottom of screen
        if (sprite.getY() > ViewPort.getInstance().getHeight() - height) {

            sprite.setY(ViewPort.getInstance().getHeight() - height);

        } else if (sprite.getY() < 0) { // Check if sprite moves off top of screen

            sprite.setY(0);
        }
    }
}
