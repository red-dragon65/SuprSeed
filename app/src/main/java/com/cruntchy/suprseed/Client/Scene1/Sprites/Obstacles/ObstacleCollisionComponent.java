package com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles;

import com.cruntchy.suprseed.Client.Scene1.Data.CameraShaker;
import com.cruntchy.suprseed.Client.Scene1.Data.GameOverData;
import com.cruntchy.suprseed.Engine.Collisions.CollisionHandler;
import com.cruntchy.suprseed.Engine.Collisions.PixelPerfectCollision;
import com.cruntchy.suprseed.Engine.Collisions.RectangleCollision;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

import java.util.List;

public class ObstacleCollisionComponent implements Collidable {

    private final List<Sprite> obstacles;
    private final Sprite hero;

    private final CollisionHandler collisionMethod;
    private final SoundMixer<String> soundEngine;

    private final CameraShaker cameraShaker;

    private final GameOverData gameOverData;


    public ObstacleCollisionComponent(Sprite hero, List<Sprite> obstacles, SoundMixer<String> soundEngine, GameOverData gameOverData) {

        this.hero = hero;
        this.obstacles = obstacles;
        this.soundEngine = soundEngine;
        this.gameOverData = gameOverData;

        // TODO: This should be injected for better flexibility...
        collisionMethod = new PixelPerfectCollision(new RectangleCollision(), 1);
        cameraShaker = new CameraShaker();
        cameraShaker.loadCameraShake();
    }


    @Override
    public void collide() {

        // See if hero touches any obstacles
        for(Sprite s : obstacles){

            if(collisionMethod.checkCollision(hero, s)){

                // Disable hero
                hero.setActive(false);

                // Stop all obstacles from moving
                for (Sprite ob : obstacles) {
                    ob.setyVel(0);
                }

                // Play sound
                soundEngine.playSound("hit");

                // Shake camera
                cameraShaker.triggerShake();

                // Notify game over has occurred
                gameOverData.setGameOver(true);
                gameOverData.setStarted(false);

                // Stop processing further collisions
                return;
            }
        }
    }
}
