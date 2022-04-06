package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Resetable;

import java.util.ArrayList;
import java.util.List;

public class LogicSystem implements Logic, Resetable, LogicRegister {

    // OPTIMIZE: Should sprites be allowed to de-register themselves?

    private final List<Logic> logicSprites;

    // Eager loading singleton
    private static final LogicSystem INSTANCE = new LogicSystem();


    // Constructor
    // Private to prevent client use of 'new' keyword
    private LogicSystem() {
        logicSprites = new ArrayList<>();
    }

    public static LogicSystem getInstance() {
        return INSTANCE;
    }

    @Override
    public void registerLogicSprite(Logic sprite) {

        logicSprites.add(sprite);
    }

    // Run logic for all registered sprites
    @Override
    public void runLogic() {

        for (Logic s : logicSprites) {
            s.runLogic();
        }
    }

    // Clear currently registered sprites
    @Override
    public void resetState() {
        logicSprites.clear();
    }
}
