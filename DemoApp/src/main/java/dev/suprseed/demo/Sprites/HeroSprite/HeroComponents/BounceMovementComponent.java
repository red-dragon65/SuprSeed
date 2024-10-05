package dev.suprseed.demo.Sprites.HeroSprite.HeroComponents;

import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.HeroSprite.FullScreenHeroTouchInput;
import dev.suprseed.Engine.Core.InputHandler.TouchInput.InputManager;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;

public class BounceMovementComponent implements ResetableComponent {

    private final Sprite sprite;
    private final float boundary = 80;
    private final float gravity = 0.05f;
    private final FullScreenHeroTouchInput screenListener;
    private final BounceData bounceData;
    private final SoundMixer<String> soundEngine;

    // Constructor
    public BounceMovementComponent(Sprite sprite, BounceData bounceData, SoundMixer<String> soundEngine, GameOverData gameOverData) {

        this.sprite = sprite;
        this.bounceData = bounceData;
        this.soundEngine = soundEngine;

        screenListener = new FullScreenHeroTouchInput(sprite, gameOverData);

        InputManager.getInstance().listenerRegister.registerObject(screenListener);
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
    private void bounce(){

        if(sprite.getY() + gravity < boundary){ // Let hero fall

            // Increase velocity of sprite downwards unless boundary is hit
            sprite.setyVel(sprite.getyVel() + gravity);

        }else{

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
                if(bounceData.getBounceValue() == 0){

                    sprite.setyVel(-sprite.getyVel());
                }else{

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
