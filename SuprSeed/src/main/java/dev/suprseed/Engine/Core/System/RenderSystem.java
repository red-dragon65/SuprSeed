package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.RegisterTypes.IAnimationRegister;
import dev.suprseed.Engine.Core.System.RegisterTypes.IImageRegister;
import dev.suprseed.Engine.Core.System.Registerables.IRenderable;
import dev.suprseed.Engine.Core.System.Registerables.IRenderableAndILayerable;
import dev.suprseed.Engine.Core.System.Registers.AnimationRegister;
import dev.suprseed.Engine.Core.System.Registers.ImageRegister;
import dev.suprseed.Engine.Lib.Images.GlobalFrameStepper;

public class RenderSystem implements IRenderable {

    // Eager loading singleton
    private static final RenderSystem INSTANCE = new RenderSystem();
    private final IImageRegister<IRenderableAndILayerable> imageRegister;
    private final IAnimationRegister animationRegister;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private RenderSystem() {

        imageRegister = new ImageRegister(new LayerableQueueComparator());

        animationRegister = new AnimationRegister();
    }


    public static RenderSystem getInstance() {
        return INSTANCE;
    }

    public IImageRegister<IRenderableAndILayerable> getImageRegister() {
        return imageRegister;
    }

    public IAnimationRegister getAnimationRegister() {
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
