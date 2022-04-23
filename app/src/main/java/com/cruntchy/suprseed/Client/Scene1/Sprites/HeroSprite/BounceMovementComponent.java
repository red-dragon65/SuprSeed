package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Client.Scene1.Data.GameOverData;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputListener;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputManager;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.CanvasData;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class BounceMovementComponent implements Movable {

    private final Sprite sprite;

    private final float boundary = 80;
    private final float gravity = 0.05f;
    private final InputListener screenListener;
    private boolean hold = false;
    private final BounceData bounceData;
    private final SoundMixer<String> soundEngine;

    // Constructor
    public BounceMovementComponent(Sprite sprite, BounceData bounceData, SoundMixer<String> soundEngine, GameOverData gameOverData) {

        this.sprite = sprite;
        this.bounceData = bounceData;
        this.soundEngine = soundEngine;

        screenListener = new InputListener() {

            @Override
            public boolean processInput(String action, MotionEvent event) {

                hold = action.equals("hold") || action.equals("drag");

                // Notify that user has triggered game to start
                if (!gameOverData.isStarted() && !gameOverData.isGameOver()) {
                    gameOverData.setStarted(true);
                }

                // Pass to 'gameRestart' input if hero is inactive
                return sprite.isActive();
            }

            @Override
            public void getRectF(RectF result) {

                float height = CanvasData.getInstance().getOriginalHeight();
                float width = CanvasData.getInstance().getOriginalWidth();

                result.set(0, 0, width, height);
            }

            // This should be above everything else
            @Override
            public int getLayerDepth() {
                return 101;
            }
        };

        InputManager.getInstance().listenerRegister.registerObject(screenListener);
    }


    @Override
    public void move() {

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

            if(hold){ // Stop hero fall

                // Save fall velocity
                if(bounceData.getBounceValue() == 0){
                    bounceData.setBounceValue(sprite.getyVel());
                }

                sprite.setyVel(0);

            }else{ // Bounce hero

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

    public void resetState() {
        bounceData.setBounceValue(0);
        hold = false;
    }
}
