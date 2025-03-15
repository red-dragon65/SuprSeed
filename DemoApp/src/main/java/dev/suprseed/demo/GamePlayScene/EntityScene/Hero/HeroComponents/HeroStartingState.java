package dev.suprseed.demo.GamePlayScene.EntityScene.Hero.HeroComponents;

import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class HeroStartingState implements Component {

    private final Sprite sprite;


    // Constructor
    public HeroStartingState(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update() {

        // Allow sprite to be active again
        sprite.setActive(true);

        // Initialize starting state
        sprite.setX(40);
        sprite.setY(30);

        sprite.setxVel(0);
        sprite.setyVel(0);
    }
}
