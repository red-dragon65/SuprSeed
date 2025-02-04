package dev.suprseed.demo.Sprites.Obstacles;

import java.util.ArrayList;
import java.util.Random;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoadable;
import dev.suprseed.Engine.Lib.Images.SpriteImage;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleComponents.ObstacleCollisionComponent;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleComponents.ObstacleMovementComponent;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleComponents.ObstacleSpawnerComponent;

public class ObstacleCollection implements LogicRunnable {


    private final ArrayList<Sprite> obstacleSprites;
    private final ArrayList<AssetBundle> obstacleImages;

    private final int maxObstacles = 15;

    private final Random rand = new Random();


    // Dependencies
    private final Component obstacleCollision;
    private final ResetableComponent obstacleSpawner;
    private final Component obstacleMovement;
    private final GameOverData gameOverData;


    public ObstacleCollection(BaseScene parentScene, AssetLoadable<AssetBundle, SpriteImage> assets, BounceData heroBounceData, Sprite hero, SoundMixer<String> soundEngine, GameOverData gameOverData) {

        this.gameOverData = gameOverData;

        obstacleSprites = new ArrayList<>();
        obstacleImages = new ArrayList<>();

        // TODO: actually load images in asset loader!!!
        // Load images here
        obstacleImages.add(assets.getAssetBundle("bat"));
        obstacleImages.add(assets.getAssetBundle("bee"));
        obstacleImages.add(assets.getAssetBundle("bird"));
        obstacleImages.add(assets.getAssetBundle("duck"));
        obstacleImages.add(assets.getAssetBundle("ghost"));

        // Initialize obstacle sprites here
        for (int i = 0; i < maxObstacles; i++) {

            obstacleSprites.add(new ObstacleSprite(parentScene, obstacleImages.get(rand.nextInt(obstacleImages.size()))));
        }


        // Initialize obstacle components
        obstacleCollision = new ObstacleCollisionComponent(hero, obstacleSprites, soundEngine, gameOverData);
        obstacleSpawner = new ObstacleSpawnerComponent(obstacleSprites, obstacleImages);
        obstacleMovement = new ObstacleMovementComponent(heroBounceData, obstacleSprites, hero);


        // Set starting state of obstacles
        obstacleSpawner.resetState();


        // Register this with the parent
        parentScene.logicRegister.registerObject(this);
    }


    @Override
    public void runLogic() {

        // Stop logic if hero dies
        if (!gameOverData.isGameOver()) {
            obstacleSpawner.update();
            obstacleMovement.update();
            obstacleCollision.update();
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void resetState() {
        obstacleSpawner.resetState();
    }
}
