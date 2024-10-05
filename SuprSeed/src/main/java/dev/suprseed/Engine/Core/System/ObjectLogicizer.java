package dev.suprseed.Engine.Core.System;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.Register.UpdatableRegister;

public class ObjectLogicizer implements UpdatableRegister<Logic> {

    private final List<Logic> logicSprites;


    // Constructor
    public ObjectLogicizer() {
        logicSprites = new ArrayList<>();
    }


    @Override
    public void registerObject(Logic sprite) {
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

            if (s.isActive()) {
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