package com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.RenderableAndLayerable;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleCollection implements Logic, RenderableAndLayerable {


    private ArrayList<Sprite> obstacleSprites;
    private ArrayList<ImageHandler> obstacleImages;
    private Sprite hero;

    private final int maxObstacles = 15;

    private Random rand = new Random();


    // Dependencies
    private Collidable obstacleCollision;
    private ObstacleSpawnerComponent obstacleSpawner;
    private Movable obstacleMovement;


    public ObstacleCollection(BaseScene parentScene, AssetLoader assets, BounceData heroBounceData, Sprite hero, SoundMixer<String> soundEngine){

        this.hero = hero;

        obstacleSprites = new ArrayList<>();
        obstacleImages = new ArrayList<>();

        // TODO: actually load images in asset loader!!!
        // Load images here
        obstacleImages.add(new ImageHandler("bat", (SpriteImage) assets.getAnimation("bat")));
        obstacleImages.add(new ImageHandler("bee", (SpriteImage) assets.getAnimation("bee")));
        obstacleImages.add(new ImageHandler("bird", (SpriteImage) assets.getAnimation("bird")));
        obstacleImages.add(new ImageHandler("duck", (SpriteImage) assets.getAnimation("duck")));
        obstacleImages.add(new ImageHandler("ghost", (SpriteImage) assets.getAnimation("ghost")));

        // Initialize obstacle sprites here
        for(int i = 0; i < maxObstacles; i++){

            obstacleSprites.add(new ObstacleSprite(parentScene, obstacleImages.get(rand.nextInt(obstacleImages.size()))));
        }


        // Initialize obstacle components
        obstacleCollision = new ObstacleCollisionComponent(hero, obstacleSprites, soundEngine);
        obstacleSpawner = new ObstacleSpawnerComponent(obstacleSprites, obstacleImages);
        obstacleMovement = new ObstacleMovementComponent(heroBounceData, obstacleSprites, hero);


        // Set starting state of obstacles
        obstacleSpawner.setStartingState();


        // Register this with the parent
        parentScene.logicRegister.registerObject(this);
        parentScene.imageRegister.registerObject(this);
    }




    @Override
    public void runLogic() {

        // Stop logic if hero dies
        if(hero.isActive()){
            obstacleSpawner.runLogic();
            obstacleMovement.move();
            obstacleCollision.collide();
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

        for(Sprite s : obstacleSprites){
            s.draw(renderer);
        }
    }

    @Override
    public boolean isDrawable() {
        return true;
    }
}
