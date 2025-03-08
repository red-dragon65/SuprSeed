package dev.suprseed.Engine.Lib.Images;

import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Player;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayerCreator;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.EngineContext;

public class SpriteAsset implements PlayerCreator {

    private SpriteImage imageSet;
    private Player player = null;
    private boolean playerInitialized = false;

    public SpriteAsset(SpriteImage imageSet) {
        this.imageSet = imageSet;
    }

    @Override
    public SpriteImage getImageSet() {
        return imageSet;
    }

    @Override
    public Player createPlayer(BaseScene parentScene, int fps) {

        // TODO: Implement this warning log
        /*
        if(Engine.loop.hasStarted()){
            EngineContext.getLogger().logMessage(ErrorType.WARN, "Warning! Attempting to create an object within the game loop! Caller: createPlayer() for tag: " + tag);
        }
         */

        if (player != null) {
            String message = "A player already exists for asset item: " + imageSet.getTag() + "! Ignoring creation!";
            EngineContext.getLogger().logMessage(ErrorType.WARN, message);
            return player;
        }

        if (imageSet.getNumImages() == 1) {

            EngineContext.getLogger().logMessage(ErrorType.WARN, "Image set: " + imageSet.getTag() + " contains only 1 image!" +
                    "Created animation player won't show any apparent animations on screen!");
        }

        player = new AnimationPlayer(parentScene, fps, imageSet.getTag(), imageSet.getNumImages());
        playerInitialized = true;

        return player;
    }

    @Override
    public Optional<Player> getPlayer() {

        // Warn the user that nothing was found
        if (player == null) {

            String message = "No player was found with tag: " + imageSet.getTag() + "! Returning null value!";
            EngineContext.getLogger().logMessage(ErrorType.WARN, message);
        }

        return Optional.ofNullable(player);
    }

    @Override
    public boolean isPlayerInitialized() {
        return playerInitialized;
    }
}
