package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.Images.Animator;
import com.cruntchy.suprseed.Engine.Images.GlobalFrameStepper;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Resetable;

import java.util.ArrayList;
import java.util.List;

public class SpriteSystem implements Renderable, Logic, Resetable, Systemizable {


    // TODO: Make this a proper singleton!

    // OPTIMIZE: Should sprites be allowed to de-register themselves?


    private final List<Logic> logicSprites;
    private final List<Sprite> renderSprites;
    private final List<Animator> animationImages;


    public SpriteSystem() {

        logicSprites = new ArrayList<>();
        renderSprites = new ArrayList<>();
        animationImages = new ArrayList<>();
    }



    // VERIFY: is this correct?
    private static class SystemSingleton {
        private static final SpriteSystem INSTANCE = new SpriteSystem();
    }

    // VERIFY: is this correct?
    public static SpriteSystem getInstance() {
        return SystemSingleton.INSTANCE;
    }



    @Override
    public void registerLogicSprite(Logic sprite){

        logicSprites.add(sprite);
    }

    @Override
    public void registerRenderSprite(Sprite sprite) {

        renderSprites.add(sprite);
    }

    public void registerAnimationImage(Animator animation) {
        animationImages.add(animation);
    }


    // Run logic for all registered sprites
    @Override
    public void runLogic() {

        for (Logic s : logicSprites) {
            s.runLogic();
        }
    }


    // Draw registered sprites
    @Override
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

        // Draw the sprites
        for (Sprite s : renderSprites) {
            //renderer.drawSprite(s);
            s.draw(renderer);
        }
    }


    // Clear currently registered sprites
    @Override
    public void resetState() {

        logicSprites.clear();
        renderSprites.clear();
    }
}
