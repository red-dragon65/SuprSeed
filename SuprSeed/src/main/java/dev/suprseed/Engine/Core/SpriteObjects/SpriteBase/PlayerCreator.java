package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

import java.util.Optional;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;

public interface PlayerCreator {

    SpriteImage getImageSet();

    Player createPlayer(BaseScene parentScene, int fps);

    Optional<Player> getPlayer();

    boolean isPlayerInitialized();
}
