package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ObjectRegister;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.UpdatableRegister;

import java.util.ArrayList;
import java.util.List;

public class LogicSystem implements UpdatableRegister<Logic> {

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
    public void registerObject(Logic sprite) {

        // Prevent this class from registering from to itself
        if (sprite == this) {

            CentralLogger.getInstance().logMessage(ErrorType.WARNING, "You can't register the register to itself!");
            return;
        }

        logicSprites.add(sprite);
    }

    @Override
    public void removeObject(Logic sprite) {
        logicSprites.remove(sprite);
    }


    // Run logic for all registered sprites
    @Override
    public void update() {

        // Run logic for active sprites
        for (Logic s : logicSprites) {

            if(s.isActive()){
                s.runLogic();
            }
        }
    }

    // Clear currently registered sprites
    @Override
    public void removeAllObjects() {
        logicSprites.clear();
    }
}
