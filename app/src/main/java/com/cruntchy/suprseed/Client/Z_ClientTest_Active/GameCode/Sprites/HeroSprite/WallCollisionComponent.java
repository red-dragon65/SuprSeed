package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HeroSprite;

import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class WallCollisionComponent implements Collidable {

    private final Sprite sprite;
    private final SoundMixer<String> soundEngine;

    // Constructor
    public WallCollisionComponent(Sprite sprite, SoundMixer<String> soundEngine) {

        this.sprite = sprite;
        this.soundEngine = soundEngine;
    }


    @Override
    public void collide() {

        // Get the current images scaled dimensions
        float width = sprite.getImageHandler().getSelectedImageSet().getScaledWidth();
        float height = sprite.getImageHandler().getSelectedImageSet().getScaledHeight();


        // Flip velocity if wall collision occurs

        // This should see if the sprite moves off the right side of the screen
        if (sprite.getX() > sprite.getCanvasScaledWidth() - width) {

            sprite.setX(sprite.getCanvasScaledWidth() - width);
            sprite.setxVel(-sprite.getxVel());
            soundEngine.playSound("bounce");

        } else if (sprite.getX() < 0) { // Check if the sprite moves off the left side of the screen

            sprite.setX(0);
            sprite.setxVel(-sprite.getxVel());
            soundEngine.playSound("bounce");
        }

        // Check if sprite moves off bottom of screen
        if (sprite.getY() > sprite.getCanvasScaledHeight() - height) {

            sprite.setY(sprite.getCanvasScaledHeight() - height);
            sprite.setyVel(-sprite.getyVel());
            soundEngine.playSound("bounce");

        } else if (sprite.getY() < 0) { // Check if sprite moves off top of screen

            sprite.setY(0);
            sprite.setyVel(-sprite.getyVel());
            soundEngine.playSound("bounce");
        }
    }
}