package dev.suprseed.demo.GamePlayScene.EntityScene.Hero;

import android.content.Context;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayBackOptions;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;
import dev.suprseed.Engine.Lib.Collisions.CollisionHandler;
import dev.suprseed.Engine.Lib.Collisions.RectangleCollision;
import dev.suprseed.Engine.Lib.Images.DefaultFPS;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.GamePlayScene.EntityScene.Hero.HeroComponents.BounceMovementComponent;
import dev.suprseed.demo.GamePlayScene.EntityScene.Hero.HeroComponents.HeroStartingState;
import dev.suprseed.demo.GamePlayScene.EntityScene.Hero.HeroComponents.TiltMovementComponent;
import dev.suprseed.demo.GamePlayScene.EntityScene.Hero.HeroComponents.WallCollisionComponent;
import dev.suprseed.demo.GamePlayScene.SharedData.BounceData;

public class Hero extends Sprite implements LogicRunnable {

    // Behavior components
    private final Component startingState;
    private final Component wallCollision;
    //private final Movable bounceMovement;
    private final ResetableComponent bounceMovement;
    private final Component tiltMovement;
    private final CollisionHandler collider;
    private final BounceData bounceData;
    private final FullScreenHeroTouchInput screenListener;

    public Hero(BaseScene parentScene, Context context, AssetBundle assetBundle, SoundMixer<String> soundEngine, BounceData bounceData, FullScreenHeroTouchInput fullScreenHeroTouchInput) {
        super(assetBundle);

        this.bounceData = bounceData;
        this.screenListener = fullScreenHeroTouchInput;
        screenListener.setActive(isActive());

        // Instantiate behavior here if you want
        // but it is probably better to dependency inject these
        startingState = new HeroStartingState(this);
        wallCollision = new WallCollisionComponent(this);
        bounceMovement = new BounceMovementComponent(this, bounceData, soundEngine, fullScreenHeroTouchInput);
        tiltMovement = new TiltMovementComponent(this, context);
        collider = new RectangleCollision();

        // Specify the animation of the asset bundles assets
        assetBundle.getSpriteAssetByTag("heroLeft")
                .createPlayer(parentScene, DefaultFPS._2.toInt())
                .setPlayOptions(PlayBackOptions.LOOP_FORWARD)
                .play();
        assetBundle.getSpriteAssetByTag("heroRight")
                .createPlayer(parentScene, DefaultFPS._2.toInt())
                .setPlayOptions(PlayBackOptions.LOOP_FORWARD)
                .play();

        // Run starting state behavior
        startingState.update();
    }

    @Override
    public void runLogic() {

        // Update velocity based on wall collisions
        wallCollision.update();

        // Calculate sprite translation
        bounceMovement.update();
        tiltMovement.update();

        // Apply sprite translation
        moveSprite();

        // Check for wall collisions
        collider.checkCollision(this, this);

        // Change sprite direction based on tilt
        if (getxVel() < 0) {
            assetBundle.trySelectingAsset("heroLeft");
        } else {
            assetBundle.trySelectingAsset("heroRight");
        }

    }

    private void moveSprite() {

        // Update to new location values
        applyXVel();
        applyYVel();
    }

    @Override
    public void setActive(boolean enabled) {
        super.setActive(enabled);

        // Stop bounceData
        bounceData.setBounceValue(0);

        // Disable the touch listener if this hero gets disabled
        screenListener.setActive(enabled);
    }

    @Override
    public void resetState() {
        startingState.update();
        bounceMovement.resetState();
    }
}
