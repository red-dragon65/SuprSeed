package dev.suprseed.Engine.Core.System.Registers;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.Registerables.ILogicRunnable;
import dev.suprseed.Engine.Core.System.RegisterTypes.ILogicRegister;

public class LogicRegister implements ILogicRegister {

    private final List<ILogicRunnable> sprites;


    // Constructor
    public LogicRegister() {
        sprites = new ArrayList<>();
    }


    @Override
    public void registerObject(ILogicRunnable sprite) {
        sprites.add(sprite);
    }

    @Override
    public void removeObject(ILogicRunnable sprite) {
        sprites.remove(sprite);
    }


    // Run logic for all registered sprites
    @Override
    public void update() {

        // Run logic for active sprites
        for (ILogicRunnable s : sprites) {

            if (s.isActive()) {
                s.runLogic();
            }
        }
    }

    // Clear currently registered sprites
    @Override
    public void removeAllObjects() {
        sprites.clear();
    }
}