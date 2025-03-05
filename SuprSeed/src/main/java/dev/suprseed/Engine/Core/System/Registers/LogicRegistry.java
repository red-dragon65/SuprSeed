package dev.suprseed.Engine.Core.System.Registers;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.RegisterTypes.LogicRegister;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;

public class LogicRegistry implements LogicRegister {

    private final List<LogicRunnable> runners;

    // Constructor
    public LogicRegistry() {
        runners = new ArrayList<>();
    }

    @Override
    public void registerObject(LogicRunnable sprite) {
        runners.add(sprite);
    }

    @Override
    public void removeObject(LogicRunnable sprite) {
        runners.remove(sprite);
    }


    // Run logic for all registered sprites
    @Override
    public void update() {

        // Run logic for active sprites
        for (LogicRunnable s : runners) {

            if (s.isActive()) {
                s.runLogic();
            }
        }
    }

    // Clear currently registered sprites
    @Override
    public void removeAllObjects() {
        runners.clear();
    }
}