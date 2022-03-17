package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.HeroSprite;

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

        // Initialize starting state
        sprite.setX(0); // This should be half the width of the screen
        sprite.setY(0); // This should be half the width of the screen from the top

        sprite.setxVel(0.4f); // This should move from the left side of the screen to the right in about 4 seconds
        sprite.setyVel(0.8f);
    }
}
