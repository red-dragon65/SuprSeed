package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.Images.Animator;
import com.cruntchy.suprseed.Engine.Images.GlobalFrameStepper;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.RenderRegister;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.UpdatableRegister;

import java.util.ArrayList;

public class RenderSystem implements Renderable {

    public final RenderRegister<RenderableAndLayerable> imageRegister;
    public final UpdatableRegister<Animator> animationRegister;

    // Eager loading singleton
    private static final RenderSystem INSTANCE = new RenderSystem();


    // Constructor
    // Private to prevent client use of 'new' keyword
    private RenderSystem() {

        imageRegister = new ImageRenderer(new ArrayList<>(), new LayerableQueueComparator());

        animationRegister = new AnimationRenderer(new ArrayList<>());
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
