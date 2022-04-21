package com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles;

import com.cruntchy.suprseed.Client.Scene1.Data.CameraShaker;
import com.cruntchy.suprseed.Engine.Collisions.CollisionHandler;
import com.cruntchy.suprseed.Engine.Collisions.PixelPerfectCollision;
import com.cruntchy.suprseed.Engine.Collisions.RectangleCollision;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

import java.util.List;

public class ObstacleCollisionComponent implements Collidable {

    private List<Sprite> obstacles;
    private Sprite hero;

    private CollisionHandler collisionMethod;
    private SoundMixer<String> soundEngine;

    private CameraShaker cameraShaker;


    public ObstacleCollisionComponent(Sprite hero, List<Sprite> obstacles, SoundMixer<String> soundEngine){

        this.hero = hero;
        this.obstacles = obstacles;
        this.soundEngine = soundEngine;

        // TODO: This should be injected for flexibility...
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
                for(Sprite ob : obstacles){
                    ob.setyVel(0);
                }

                // Play sound
                soundEngine.playSound("hit");

                // Shake camera
                cameraShaker.triggerShake();

                // TODO:
                //  - Do camera shake
                //  - Show game over overlay

                // Stop processing collisions
                return;
            }
        }
    }
}
