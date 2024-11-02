package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.RegisterTypes.AnimationRegister;
import dev.suprseed.Engine.Core.System.RegisterTypes.ImageRegister;
import dev.suprseed.Engine.Core.System.Registerables.Renderable;
import dev.suprseed.Engine.Core.System.Registerables.RenderableAndLayerable;
import dev.suprseed.Engine.Core.System.Registers.AnimationRegistry;
import dev.suprseed.Engine.Core.System.Registers.ImageRegistry;
import dev.suprseed.Engine.Lib.Images.GlobalFrameStepper;

public class RenderSystem implements Renderable {

    // Eager loading singleton
    private static final RenderSystem INSTANCE = new RenderSystem();
    private final ImageRegister<RenderableAndLayerable> imageRegister;
    private final AnimationRegister animationRegister;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private RenderSystem() {

        imageRegister = new ImageRegistry(new LayerableQueueComparator());

        animationRegister = new AnimationRegistry();
    }


    public static RenderSystem getInstance() {
        return INSTANCE;
    }

    public ImageRegister<RenderableAndLayerable> getImageRegister() {
        return imageRegister;
    }

    public AnimationRegister getAnimationRegister() {
        return animationRegister;
    }

    // Draw registered sprites
    @Override
    public void draw(RenderHandler renderer) {

        // Make sure renderHandler is set
        if (renderer == null) {

            CentralLogger.getInstance().logMessage(ErrorType.FATAL, "The render is null! The engine is borked!");
            return;
        }


        animationRegister.update();
        imageRegister.update(renderer);


        // Update animation frames
        /*
        Logic loop -> render -> generate next frames -> logic loop -> render

        This has to go after rendering / before logic to make sure logic matches with given frame
         */
        GlobalFrameStepper.getInstance().moveToNextFrame();
    }
}
