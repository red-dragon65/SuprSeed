package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.Images.Animator;
import com.cruntchy.suprseed.Engine.Images.GlobalFrameStepper;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Resetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RenderSystem implements Resetable, RenderRegister {

    // TODO: Make this a proper singleton!

    // OPTIMIZE: Should sprites be allowed to de-register themselves?


    private final List<Renderable> renderQueue;
    private final List<Animator> animationImages;
    private final Comparator<Renderable> rqic;
    private boolean isQueueSynced = false;


    // Constructor
    public RenderSystem() {
        renderQueue = new ArrayList<>();
        animationImages = new ArrayList<>();
        rqic = new RenderQueueComparator();
    }

    // VERIFY: is this correct?
    public static RenderSystem getInstance() {
        return RenderSingleton.INSTANCE;
    }

    @Override
    public void registerRenderSprite(Renderable sprite) {

        isQueueSynced = false;
        renderQueue.add(sprite);
    }

    @Override
    public void registerAnimationImage(Animator animation) {
        animationImages.add(animation);
    }

    // Draw registered sprites
    public void draw(RenderHandler renderer) {

        // Update animation frames
        GlobalFrameStepper.moveToNextFrame();

        // Make sure renderHandler is set
        if (renderer == null) {

            CentralLogger.logMessage(ErrorType.FATAL_ERROR, "The render is null! The engine is borked!");
            return;
        }


        // Update all animations to the next frame
        for (Animator ani : animationImages) {
            ani.generateNextFrame();
        }


        // Re-sort the sprite list if necessary
        if (!isQueueSynced) {

            renderQueue.sort(rqic);

            isQueueSynced = true;
        }

        // Draw the sprites
        for (Renderable sprite : renderQueue) {

            // This is not the desired behavior
            // This does not allow the user to override the drawing behavior
            //renderer.drawSprite(s);

            sprite.draw(renderer);
        }
    }

    // Clear currently registered sprites
    @Override
    public void resetState() {
        renderQueue.clear();
    }

    // VERIFY: is this correct?
    private static class RenderSingleton {
        private static final RenderSystem INSTANCE = new RenderSystem();
    }
}
