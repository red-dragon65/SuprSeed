package dev.suprseed.demo.GamePlayScene.EntityScene.Hero.HeroComponents;

import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.GamePlayScene.SharedData.BounceData;
import dev.suprseed.demo.GamePlayScene.EntityScene.Hero.FullScreenHeroTouchInput;

public class BounceMovementComponent implements ResetableComponent {

    private final Sprite sprite;
    private final float boundary = 80;
    private final float gravity = 0.05f;
    private final FullScreenHeroTouchInput screenListener;
    private final BounceData bounceData;
    private final SoundMixer<String> soundEngine;

    // Constructor
    public BounceMovementComponent(Sprite sprite, BounceData bounceData, SoundMixer<String> soundEngine, FullScreenHeroTouchInput fullScreenHeroTouchInput) {

        this.sprite = sprite;
        this.bounceData = bounceData;
        this.soundEngine = soundEngine;
        this.screenListener = fullScreenHeroTouchInput;
    }


    @Override
    public void update() {

        // Bounce hero if necessary
        bounce();
    }


    /*
    If no input, then bounce sprite

    If hold input, then don't bounce sprite
    */
    private void bounce() {

        if (sprite.getY() + gravity < boundary) { // Let hero fall

            // Increase velocity of sprite downwards unless boundary is hit
            sprite.setyVel(sprite.getyVel() + gravity);

        } else {

            // Reset to boundary limit
            sprite.setY(boundary);

            if (screenListener.isHold()) { // Stop hero fall

                // Save fall velocity
                if (bounceData.getBounceValue() == 0) {
                    bounceData.setBounceValue(sprite.getyVel());
                }

                sprite.setyVel(0);

            } else { // Bounce hero

                // Hero is past boundary. Reverse velocity for bounce.
                if (bounceData.getBounceValue() == 0) {

                    sprite.setyVel(-sprite.getyVel());
                } else {

                    sprite.setyVel(-bounceData.getBounceValue());

                    bounceData.setBounceValue(0);
                }

                // Play bounce sound
                soundEngine.playSound("bounce");

            }

        }
    }

    @Override
    public void resetState() {
        bounceData.setBounceValue(0);
        screenListener.setHold(false);
    }
}
