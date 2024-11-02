package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.System.RegisterTypes.LogicRegister;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;
import dev.suprseed.Engine.Core.System.Registers.LogicRegistry;

public class LogicSystem implements LogicRunnable {

    // Eager loading singleton
    private static final LogicSystem INSTANCE = new LogicSystem();
    private final LogicRegister logicRegister;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private LogicSystem() {
        logicRegister = new LogicRegistry();
    }

    public static LogicSystem getInstance() {
        return INSTANCE;
    }

    public LogicRegister getLogicRegister() {
        return logicRegister;
    }

    @Override
    public void runLogic() {
        logicRegister.update();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
