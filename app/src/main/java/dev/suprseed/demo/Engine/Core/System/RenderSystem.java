package dev.suprseed.demo.Engine.Core.System;

import dev.suprseed.demo.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.demo.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.demo.Engine.Core.System.Register.RenderRegister;
import dev.suprseed.demo.Engine.Core.System.Register.UpdatableRegister;
import dev.suprseed.demo.Engine.Lib.Images.Animator;
import dev.suprseed.demo.Engine.Lib.Images.GlobalFrameStepper;

public class RenderSystem implements Renderable {

    public final RenderRegister<RenderableAndLayerable> imageRegister;
    public final UpdatableRegister<Animator> animationRegister;

    // Eager loading singleton
    private static final RenderSystem INSTANCE = new RenderSystem();


    // Constructor
    // Private to prevent client use of 'new' keyword
    private RenderSystem() {

        imageRegister = new ImageRenderer(new LayerableQueueComparator());

        animationRegister = new AnimationRenderer();
    }


    public static RenderSystem getInstance() {
        return INSTANCE;
    }


    // Draw registered sprites
    @Override
    public void draw(RenderHandler renderer) {

        // Make sure renderHandler is set
        if (renderer == null) {

            CentralLogger.getInstance().logMessage(ErrorType.FATAL_ERROR, "The render is null! The engine is borked!");
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
