package dev.suprseed.demo.Sprites.Obstacles;

import java.util.ArrayList;
import java.util.Random;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.Logic;
import dev.suprseed.Engine.Core.System.RenderableAndLayerable;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleComponents.ObstacleCollisionComponent;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleComponents.ObstacleMovementComponent;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleComponents.ObstacleSpawnerComponent;

public class ObstacleCollection implements Logic, RenderableAndLayerable {


    private final ArrayList<Sprite> obstacleSprites;
    private final ArrayList<AssetBundle> obstacleImages;

    private final int maxObstacles = 15;

    private final Random rand = new Random();


    // Dependencies
    private final Component obstacleCollision;
    private final ResetableComponent obstacleSpawner;
    private final Component obstacleMovement;
    private final GameOverData gameOverData;


    public ObstacleCollection(BaseScene parentScene, AssetLoader assets, BounceData heroBounceData, Sprite hero, SoundMixer<String> soundEngine, GameOverData gameOverData) {

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
        parentScene.imageRegister.registerObject(this);
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

    @Override
    public int getLayerDepth() {
        return 0;
    }

    @Override
    public void draw(RenderHandler renderer) {

        for (Sprite s : obstacleSprites) {
            s.draw(renderer);
        }
    }

    @Override
    public boolean isDrawable() {
        return true;
    }

    public void resetState() {
        obstacleSpawner.resetState();
    }
}
