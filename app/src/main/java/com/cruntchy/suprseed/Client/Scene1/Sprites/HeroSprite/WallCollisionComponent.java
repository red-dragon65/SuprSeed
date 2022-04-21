package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class WallCollisionComponent implements Collidable {

    private final Sprite sprite;

    // Constructor
    public WallCollisionComponent(Sprite sprite) {

        this.sprite = sprite;
    }


    @Override
    public void collide() {

        // Get the current images scaled dimensions
        float width = sprite.getImageHandler().getSelectedImageSet().getScaledWidth();
        float height = sprite.getImageHandler().getSelectedImageSet().getScaledHeight();


        // Prevent hero from moving off left/right side of screen

        // This should see if the sprite moves off the right side of the screen
        if (sprite.getX() > sprite.getCanvasScaledWidth() - width) {

            sprite.setX(sprite.getCanvasScaledWidth() - width);

        } else if (sprite.getX() < 0) { // Check if the sprite moves off the left side of the screen

            sprite.setX(0);
        }



        // Prevent sprite from moving off bottom/top of screen

        // Check if sprite moves off bottom of screen
        if (sprite.getY() > sprite.getCanvasScaledHeight() - height) {

            sprite.setY(sprite.getCanvasScaledHeight() - height);

        } else if (sprite.getY() < 0) { // Check if sprite moves off top of screen

            sprite.setY(0);
        }
    }
}
