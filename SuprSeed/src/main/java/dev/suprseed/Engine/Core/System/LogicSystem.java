package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.System.RegisterTypes.ILogicRegister;
import dev.suprseed.Engine.Core.System.Registerables.ILogicRunnable;
import dev.suprseed.Engine.Core.System.Registers.LogicRegister;

public class LogicSystem implements ILogicRunnable {

    // Eager loading singleton
    private static final LogicSystem INSTANCE = new LogicSystem();
    private final ILogicRegister logicRegister;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private LogicSystem() {
        logicRegister = new LogicRegister();
    }

    public static LogicSystem getInstance() {
        return INSTANCE;
    }

    public ILogicRegister getLogicRegister() {
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
