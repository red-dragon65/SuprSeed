package dev.suprseed.demo.Sprites.HeroSprite;

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
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.HeroSprite.HeroComponents.BounceMovementComponent;
import dev.suprseed.demo.Sprites.HeroSprite.HeroComponents.TiltMovementComponent;
import dev.suprseed.demo.Sprites.HeroSprite.HeroComponents.WallCollisionComponent;

public class Hero extends Sprite implements LogicRunnable {

    // Behavior components
    private final Component startingState;
    private final Component wallCollision;
    //private final Movable bounceMovement;
    private final ResetableComponent bounceMovement;
    private final Component tiltMovement;
    private final CollisionHandler collider;
    private final BounceData bounceData;


    public Hero(BaseScene parentScene, Context context, AssetBundle assetBundle, SoundMixer<String> soundEngine, BounceData bounceData, GameOverData gameOverData) {
        super(assetBundle);

        // Show collision boxes
        //CollisionDiagnosticsOverlay.getInstance().enable();

        this.bounceData = bounceData;

        // Instantiate behavior here if you want
        // but it is probably better to dependency inject these
        startingState = new HeroStartingState(this);
        wallCollision = new WallCollisionComponent(this);
        bounceMovement = new BounceMovementComponent(this, bounceData, soundEngine, gameOverData);
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
    }

    @Override
    public void resetState() {
        startingState.update();
        bounceMovement.resetState();
    }
}
