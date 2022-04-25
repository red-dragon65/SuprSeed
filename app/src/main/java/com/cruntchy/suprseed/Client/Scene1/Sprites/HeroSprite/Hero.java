package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Client.Scene1.Data.GameOverData;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.DefaultComponents.ResetableComponent;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.Core.System.Logic;
import com.cruntchy.suprseed.Engine.Lib.Collisions.CollisionHandler;
import com.cruntchy.suprseed.Engine.Lib.Collisions.RectangleCollision;
import com.cruntchy.suprseed.Engine.Lib.SoundPlayer.SoundMixer;

public class Hero extends Sprite implements Logic {

    // Behavior components
    private final Component startingState;
    private final Component wallCollision;
    //private final Movable bounceMovement;
    private final ResetableComponent bounceMovement;
    private final Component tiltMovement;
    private final CollisionHandler collider;
    private final BounceData bounceData;


    public Hero(BaseScene parentScene, ImageHandler imageHandler, SoundMixer<String> soundEngine, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, imageHandler);

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
