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


    // OPTIMIZE: Should sprites be allowed to de-register themselves?


    private final List<Renderable> renderQueue;
    private final List<Animator> animationImages;
    private final Comparator<Layerable> layerComparer;
    private boolean isQueueSynced = false;

    // Eager loading singleton
    private static final RenderSystem INSTANCE = new RenderSystem();

    // Constructor
    // Private to prevent client use of 'new' keyword
    private RenderSystem() {
        renderQueue = new ArrayList<>();
        animationImages = new ArrayList<>();
        layerComparer = new LayerableQueueComparator();
    }


    public static RenderSystem getInstance() {
        return INSTANCE;
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

        // Make sure renderHandler is set
        if (renderer == null) {

            CentralLogger.getInstance().logMessage(ErrorType.FATAL_ERROR, "The render is null! The engine is borked!");
            return;
        }


        // Update all animations to the next frame
        for (Animator ani : animationImages) {
            ani.generateNextFrame();
        }


        // Re-sort the sprite list if necessary
        if (!isQueueSynced) {

            renderQueue.sort(layerComparer);

            isQueueSynced = true;
        }

        // Draw the sprites
        for (Renderable sprite : renderQueue) {

            // This is not the desired behavior
            // This does not allow the user to override the drawing behavior
            //renderer.drawSprite(s);

            sprite.draw(renderer);
        }

        // Update animation frames
        /*
        Logic loop -> render -> generate next frames -> logic loop -> render

        This has to go after rendering / before logic to make sure logic matches with given frame
         */
        GlobalFrameStepper.getInstance().moveToNextFrame();
    }

    // Clear currently registered sprites
    @Override
    public void resetState() {
        renderQueue.clear();
    }
}
