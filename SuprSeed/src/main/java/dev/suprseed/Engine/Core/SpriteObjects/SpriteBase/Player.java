package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

public interface Player {

    int getCurrentFrameIndex();

    Player setStartingFrame(int startingFrame);

    int getNumFrames();

    int getFps();

    Player setFps(int fps);

    PlayBackOptions getPlayOptions();

    Player setPlayOptions(PlayBackOptions playOptions);

    boolean isFirstFrame();

    boolean isLastFrame();

    Player play();

    Player pause();

    Player reset();

    Player hardReset();
}
