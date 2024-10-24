package dev.suprseed.demo.Sprites.HeroSprite;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.Registerables.ILogicRunnable;
import dev.suprseed.Engine.Lib.Collisions.CollisionHandler;
import dev.suprseed.Engine.Lib.Collisions.RectangleCollision;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.HeroSprite.HeroComponents.BounceMovementComponent;
import dev.suprseed.demo.Sprites.HeroSprite.HeroComponents.TiltMovementComponent;
import dev.suprseed.demo.Sprites.HeroSprite.HeroComponents.WallCollisionComponent;

public class Hero extends Sprite implements ILogicRunnable {

    // Behavior components
    private final Component startingState;
    private final Component wallCollision;
    //private final Movable bounceMovement;
    private final ResetableComponent bounceMovement;
    private final Component tiltMovement;
    private final CollisionHandler collider;
    private final BounceData bounceData;


    public Hero(BaseScene parentScene, AssetBundle assetBundle, SoundMixer<String> soundEngine, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, assetBundle);

        // Show collision boxes
        //CollisionDiagnosticsOverlay.getInstance().enable();

        this.bounceData = bounceData;

        // Instantiate behavior here if you want
        // but it is probably better to dependency inject these
        startingState = new HeroStartingState(this);
        wallCollision = new WallCollisionComponent(this);
        bounceMovement = new BounceMovementComponent(this, bounceData, soundEngine, gameOverData);
        tiltMovement = new TiltMovementComponent(this, parentScene.getContext());
        collider = new RectangleCollision();

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
            assetBundle.trySelectingImageSet("heroLeft");
        } else {
            assetBundle.trySelectingImageSet("heroRight");
        }

    }

    private void moveSprite() {

        // Update to new location values
        setX(getX() + getxVel());
        setY(getY() + getyVel());
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
