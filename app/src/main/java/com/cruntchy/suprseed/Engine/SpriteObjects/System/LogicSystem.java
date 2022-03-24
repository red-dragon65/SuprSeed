package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Resetable;

import java.util.ArrayList;
import java.util.List;

public class LogicSystem implements Logic, Resetable, LogicRegister {

    // TODO: Make this a proper singleton!

    // OPTIMIZE: Should sprites be allowed to de-register themselves?


    private final List<Logic> logicSprites;


    // Constructor
    public LogicSystem() {
        logicSprites = new ArrayList<>();
    }

    // VERIFY: is this correct?
    public static LogicSystem getInstance() {
        return LogicSingleton.INSTANCE;
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

    // VERIFY: is this correct?
    private static class LogicSingleton {
        private static final LogicSystem INSTANCE = new LogicSystem();
    }
}
