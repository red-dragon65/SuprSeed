package dev.suprseed.Engine.Core.System.Registers;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.RegisterTypes.LogicRegister;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;

public class LogicRegistry implements LogicRegister {

    private final List<LogicRunnable> sprites;


    // Constructor
    public LogicRegistry() {
        sprites = new ArrayList<>();
    }


    @Override
    public void registerObject(LogicRunnable sprite) {
        sprites.add(sprite);
    }

    @Override
    public void removeObject(LogicRunnable sprite) {
        sprites.remove(sprite);
    }


    // Run logic for all registered sprites
    @Override
    public void update() {

        // Run logic for active sprites
        for (LogicRunnable s : sprites) {

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