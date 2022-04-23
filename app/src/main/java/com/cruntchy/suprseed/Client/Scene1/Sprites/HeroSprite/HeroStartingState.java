package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.StartingState;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class HeroStartingState implements StartingState {

    private final Sprite sprite;


    // Constructor
    public HeroStartingState(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void setStartingState() {

        // Allow sprite to be active again
        sprite.setActive(true);

        // Initialize starting state
        sprite.setX(50);
        sprite.setY(30);

        sprite.setxVel(0);
        sprite.setyVel(0);
    }
}
