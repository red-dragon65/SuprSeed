package dev.suprseed.Engine.Lib.Images;

import java.util.ArrayList;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Animator;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayBackOptions;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Player;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.Lib.AssetLoader.FPSMismatchException;

public class AnimationPlayer implements Animator, Player {

    // Playback states
    private int fps;
    private PlayBackOptions playOptions;
    private boolean isFirstFrame = true;
    private boolean isLastFrame = false;
    private boolean forwardDirection = false;
    private boolean play = false;
    private int startingFrameIndex = -1;

    // Playback data
    private int frameDelay = 0;
    private int currentFrameIndex = 0;
    private int targetFrameTime = 0;
    private int frameCounter = 0;

    private int numFrames = 0;


    public AnimationPlayer(BaseScene parentScene, int fps, String spriteImageTag, int numFrames) {

        this.targetFrameTime = EngineContext.getRenderSystem().getTargetFrameTime();
        this.numFrames = numFrames;

        // Set the default playback option
        setPlayOptions(PlayBackOptions.LOOP_FORWARD);

        init(parentScene, fps, spriteImageTag);
    }


    private void init(BaseScene parentScene, int fps, String tag) {

        // Register with the parent scene
        parentScene.animationRegister.registerObject(this);

        // Note: the below code should never throw an exception unless the FPS enums have been messed with
        try {
            String tagInfo = "{tag == " + tag + "}";

            if (fps < 1) {
                throw new FPSMismatchException("An animations fps must be greater than 0 for animation: " + tagInfo);
            }

            //Make sure fps fits within frame time
            if (targetFrameTime % fps != 0) {
                String message = "The given animation " + tagInfo + " fps: " + fps + " does not fit within the frame time of: " + targetFrameTime;
                throw new FPSMismatchException(message);
            }

        } catch (FPSMismatchException e) {

            // Recover error by using next closest valid fps available
            fps = findValidFps(fps);

            String message = "INVALID FPS! Changing bitmap animation fps to nearest valid number: " + fps;
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message, e);
        }

        this.fps = fps;

        frameDelay = targetFrameTime / fps;
    }

    // Find the next nearest valid frame rate
    private int findValidFps(int fps) {

        // Prevent negative fps
        if (fps < 1) {
            return 1;
        }

        // Get a list of valid frame times
        ArrayList<Integer> validFrameTimes = validFrameTimeGenerator();

        int closestValidFrameTime = validFrameTimes.get(0);
        int lowestDiff = Math.abs(fps - validFrameTimes.get(0));

        // Find the closest valid frame time to the requested frame time
        for (Integer i : validFrameTimes) {

            if (Math.abs(fps - i) < lowestDiff) {
                lowestDiff = Math.abs(fps - i);
                closestValidFrameTime = i;
            }
        }

        return closestValidFrameTime;
    }

    // Generate valid frame rates for the given target frame time
    private ArrayList<Integer> validFrameTimeGenerator() {

        ArrayList<Integer> validFrameTimes = new ArrayList<>();

        for (int i = 1; i <= targetFrameTime; i++) {

            if (targetFrameTime % i == 0) {
                validFrameTimes.add(i);
            }
        }

        return validFrameTimes;
    }


    private void moveForward() {
        // Move forward
        currentFrameIndex++;
    }

    private void moveBackwards() {
        // Move backwards
        currentFrameIndex--;
    }

    @Override
    public void generateNextFrame() {

        if (!play) {
            return;
        }

        // Calculate next frame if fps target is hit
        if (frameCounter % frameDelay == 0) {

            switch (playOptions) {
                case LOOP_FORWARD:

                    // Loop over forward
                    if (isLastFrame) {
                        currentFrameIndex = 0;
                        break;
                    }

                case PLAY_FORWARD:
                    moveForward();
                    break;

                case LOOP_REVERSE:

                    // Loop over reverse
                    if (isFirstFrame) {
                        currentFrameIndex = numFrames - 1;
                        break;
                    }

                case PLAY_REVERSE:
                    moveBackwards();
                    break;

                case BOUNCE_FORWARD:
                case BOUNCE_REVERSE:

                    if (isLastFrame) {
                        forwardDirection = false;
                    }

                    if (isFirstFrame) {
                        forwardDirection = true;
                    }

                    if (forwardDirection) {

                        moveForward();
                    } else {

                        moveBackwards();
                    }

                    break;
            }


            frameCounter = 0;
        }

        // Mark whether starting or ending frame has been hit
        if (currentFrameIndex >= numFrames - 1) {
            isLastFrame = true;
            isFirstFrame = false;
            currentFrameIndex = numFrames - 1;
        } else if (currentFrameIndex <= 0) {
            isFirstFrame = true;
            isLastFrame = false;
            currentFrameIndex = 0;
        } else {
            isFirstFrame = false;
            isLastFrame = false;
        }

        frameCounter++;
    }

    @Override
    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    @Override
    public Player setStartingFrame(int startingFrameIndex) {

        if (startingFrameIndex < 0) {
            startingFrameIndex = 0;

            String message = "The given starting frame value: " + startingFrameIndex + " is out of bounds! Setting starting frame to 0!";
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);
        }

        if (startingFrameIndex > numFrames - 1) {
            startingFrameIndex = numFrames;

            String message = "The given starting frame value: " + startingFrameIndex + " is out of bounds! Setting starting frame to " + (numFrames - 1) + "!";
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);
        }

        this.startingFrameIndex = startingFrameIndex;
        currentFrameIndex = startingFrameIndex;

        return this;
    }

    @Override
    public int getNumFrames() {
        return numFrames;
    }

    @Override
    public int getFps() {
        return fps;
    }

    @Override
    public Player setFps(int fps) {

        // Note: the below code should never throw an exception unless the FPS enums have been messed with
        try {
            if (fps < 1) {
                throw new FPSMismatchException("An animations fps must be greater than 0!");
            }

            //Make sure fps fits within frame time
            if (targetFrameTime % fps != 0) {
                String message = "The given animation fps: " + fps + " does not fit within the frame time of: " + targetFrameTime;
                throw new FPSMismatchException(message);
            }

        } catch (FPSMismatchException e) {

            // Recover error by using next closest valid fps available
            fps = findValidFps(fps);

            String message = "INVALID FPS! Changing bitmap animation fps to nearest valid number: " + fps;
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message, e);
        }

        this.fps = fps;

        frameDelay = targetFrameTime / fps;

        return this;
    }

    @Override
    public Player setPlayOptions(PlayBackOptions playOptions) {

        // Set the starting frame
        switch (playOptions) {
            case LOOP_FORWARD:
            case PLAY_FORWARD:
            case BOUNCE_FORWARD:
                forwardDirection = true;

                if (startingFrameIndex == -1) {
                    currentFrameIndex = 0;
                } else {
                    currentFrameIndex = startingFrameIndex;
                }
                break;
            case LOOP_REVERSE:
            case PLAY_REVERSE:
            case BOUNCE_REVERSE:
                forwardDirection = false;

                if (startingFrameIndex == -1) {
                    currentFrameIndex = numFrames - 1;
                } else {
                    currentFrameIndex = startingFrameIndex;
                }
                break;
        }

        this.playOptions = playOptions;

        return this;
    }

    @Override
    public PlayBackOptions getPlayOptions() {
        return playOptions;
    }

    @Override
    public boolean isFirstFrame() {
        return isFirstFrame;
    }

    @Override
    public boolean isLastFrame() {
        return isLastFrame;
    }

    @Override
    public Player play() {
        this.play = true;

        return this;
    }

    @Override
    public Player pause() {
        this.play = false;

        return this;
    }

    @Override
    public Player reset() {
        setPlayOptions(playOptions);
        play = false;

        return this;
    }

    @Override
    public Player hardReset() {
        reset();
        startingFrameIndex = -1;

        return this;
    }
}
