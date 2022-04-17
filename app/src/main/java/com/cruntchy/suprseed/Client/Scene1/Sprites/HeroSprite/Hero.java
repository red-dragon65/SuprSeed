package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

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
    private final Movable applyVelocity;
    private final CollisionHandler collider;


    public Hero(BaseScene parentScene, ImageHandler imageHandler, SoundMixer<String> soundEngine) {
        super(parentScene, imageHandler);

        // Instantiate behavior here if you want
        // but it is probably better to dependency inject these
        startingState = new HeroStartingState(this);
        wallCollision = new WallCollisionComponent(this, soundEngine);
        applyVelocity = new VelocityMovementComponent(this);


        // Run starting state behavior
        startingState.setStartingState();

        //CollisionDiagnosticsOverlay.getInstance().enable();

        collider = new RectangleCollision();
    }

    @Override
    public void runLogic() {

        // Update velocity based on wall collisions
        wallCollision.collide();

        // Apply final sprite movement
        applyVelocity.move();

        collider.checkCollision(this, this);
    }

}
