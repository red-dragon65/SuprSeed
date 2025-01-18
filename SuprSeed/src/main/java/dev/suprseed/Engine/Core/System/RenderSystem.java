package dev.suprseed.Engine.Core.System;


import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.RegisterTypes.AnimationRegister;
import dev.suprseed.Engine.Core.System.RegisterTypes.ImageRegister;
import dev.suprseed.Engine.Core.System.Registerables.Renderable;
import dev.suprseed.Engine.Core.System.Registers.AnimationRegistry;
import dev.suprseed.Engine.Core.System.Registers.ImageRegistry;

public class RenderSystem implements Renderable {

    // Eager loading singleton
    private static final RenderSystem INSTANCE = new RenderSystem();
    private final ImageRegister imageRegister;
    private final AnimationRegister animationRegister;

    private int targetFrameTime = 30;
    private int tickScaler = 0;
    private int frameCounter = 0;
    private int refreshRate = 0;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private RenderSystem() {

        imageRegister = new ImageRegistry(new LayerableQueueComparator());

        animationRegister = new AnimationRegistry();
    }


    public static RenderSystem getInstance() {
        return INSTANCE;
    }

    public ImageRegister getImageRegister() {
        return imageRegister;
    }

    public AnimationRegister getAnimationRegister() {
        return animationRegister;
    }

    public void setTargetFrameTime(int targetFrameTime){

        CentralLogger.getInstance().logMessage(ErrorType.INFO, "The render animation target frame time was changed from: (" + this.targetFrameTime + ") to: (" + targetFrameTime + ")");

        this.targetFrameTime = targetFrameTime;

        notifyRefreshRate(refreshRate);
    }

    public int getTargetFrameTime(){
        return targetFrameTime;
    }

    public void notifyRefreshRate(int refreshRate){

        this.refreshRate = refreshRate;

        tickScaler = Math.round((float) refreshRate / targetFrameTime);

        CentralLogger.getInstance().logMessage(ErrorType.INFO, "Updating the render animation scaler to scale against the device refresh rate of: " + refreshRate);

        if(tickScaler < 1){
            tickScaler = 1;
            CentralLogger.getInstance().logMessage(ErrorType.ERROR, "The target frame time of (" + targetFrameTime
                    + ") should not be greater than the refresh rate of (" + refreshRate + ")!" +
                    "\nLocking the target frame time to the refresh rate!");
        }
    }

    // Draw registered sprites
    @Override
    public void draw(RenderHandler renderer) {

        // Make sure renderHandler is set
        if (renderer == null) {

            CentralLogger.getInstance().logMessage(ErrorType.FATAL, "The RenderHandler is null! The engine cannot draw anything!");
            return;
        }

        /*
        Scales the refresh 'tick rate' to match the animations frame time.
        Runs in render code rather than logic in order to de-couple the animation timings
        from the logic tick rate.
         */
        if(frameCounter % tickScaler == 0){

            animationRegister.update();
            frameCounter = 0;
        }
        frameCounter++;

        imageRegister.update(renderer);
    }
}
