package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Engine.Collisions.CollisionHandler;
import com.cruntchy.suprseed.Engine.Collisions.RectangleCollision;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.StartingState;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;

public class Hero extends Sprite implements Logic {

    // Behavior components
    private final StartingState startingState;
    private final Collidable wallCollision;
    //private final Movable bounceMovement;
    private final BounceMovementComponent bounceMovement;
    private final Movable tiltMovement;
    private final CollisionHandler collider;
    private final BounceData bounceData;


    public Hero(BaseScene parentScene, ImageHandler imageHandler, SoundMixer<String> soundEngine, BounceData bounceData) {
        super(parentScene, imageHandler);

        this.bounceData = bounceData;

        // Instantiate behavior here if you want
        // but it is probably better to dependency inject these
        startingState = new HeroStartingState(this);
        wallCollision = new WallCollisionComponent(this);
        bounceMovement = new BounceMovementComponent(this, bounceData, soundEngine);
        tiltMovement = new TiltMovementComponent(this, parentScene.getContext());


        // Run starting state behavior
        startingState.setStartingState();

        //CollisionDiagnosticsOverlay.getInstance().enable();

        collider = new RectangleCollision();
    }

    @Override
    public void runLogic() {

        // Update velocity based on wall collisions
        wallCollision.collide();

        // Apply sprite transform
        bounceMovement.move();
        tiltMovement.move();

        // Update to new location values
        setX(getX() + getxVel());
        setY(getY() + getyVel());

        // Check for wall collisions
        collider.checkCollision(this, this);
    }

    @Override
    public void setActive(boolean enabled) {
        super.setActive(enabled);

        // Stop bounceData
        bounceData.setBounceValue(0);
    }

    public void resetState() {
        startingState.setStartingState();
        bounceMovement.resetState();
    }
}
