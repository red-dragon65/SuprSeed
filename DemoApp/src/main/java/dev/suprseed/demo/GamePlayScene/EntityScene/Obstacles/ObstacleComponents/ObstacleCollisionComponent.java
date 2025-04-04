package dev.suprseed.demo.GamePlayScene.EntityScene.Obstacles.ObstacleComponents;

import java.util.List;

import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.Collisions.CollisionHandler;
import dev.suprseed.Engine.Lib.Collisions.PixelPerfectCollision;
import dev.suprseed.Engine.Lib.Collisions.RectangleCollision;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;
import dev.suprseed.demo.Utils.CameraShaker;

public class ObstacleCollisionComponent implements Component {

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

        collisionMethod = new PixelPerfectCollision(new RectangleCollision(), 4);
        cameraShaker = new CameraShaker();
        cameraShaker.loadCameraShake();
    }


    @Override
    public void update() {

        // See if hero touches any obstacles
        for (Sprite s : obstacles) {

            if (collisionMethod.checkCollision(hero, s)) {

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
