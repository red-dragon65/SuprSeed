package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Renderable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.ResetState;

import java.util.ArrayList;
import java.util.List;

public class SpriteSystem implements Renderable, Logic, ResetState, Systemizable{


    // TODO: Make this a proper singleton!

    // OPTIMIZE: Should sprites be allowed to de-register themselves?


    private List<Collidable> collisionSprites;
    private List<Logic> logicSprites;
    private List<Movable> movingSprites;
    private List<Sprite> renderSprites;


    public SpriteSystem(){

        collisionSprites = new ArrayList<>();
        logicSprites = new ArrayList<>();
        movingSprites = new ArrayList<>();
        renderSprites = new ArrayList<>();
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
    public void registerCollisionSprite(Collidable sprite){

        collisionSprites.add(sprite);
    }

    @Override
    public void registerLogicSprite(Logic sprite){

        logicSprites.add(sprite);
    }

    @Override
    public void registerMovingSprite(Movable sprite){

        movingSprites.add(sprite);
    }

    @Override
    public void registerRenderSprite(Sprite sprite){

        renderSprites.add(sprite);
    }





    // Run logic for all registered sprites
    @Override
    public void runLogic() {

        for(Logic s : logicSprites){
            s.runLogic();
        }

        for(Movable s : movingSprites){
            s.move();
        }

        for(Collidable s : collisionSprites){
            s.collide();
        }
    }


    // Draw registered sprites
    @Override
    public void draw(RenderHandler renderer) {

        // Verify renderHandler
        if(renderer == null){

            CentralLogger.logMessage(ErrorType.FATAL_ERROR, "The render is null! The engine is borked!");
            return;
        }


        // Draw the sprites
        for(Sprite s : renderSprites){
            //renderer.drawSprite(s);
            s.draw(renderer);
        }
    }


    // Clear currently registered sprites
    @Override
    public void resetState() {

        collisionSprites.clear();
        logicSprites.clear();
        movingSprites.clear();
        renderSprites.clear();
    }
}
