package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.System.Registerables.ILogicRunnable;
import dev.suprseed.Engine.Core.System.Registers.LogicRegister;
import dev.suprseed.Engine.Core.System.RegisterTypes.ILogicRegister;

public class LogicSystem implements ILogicRunnable {

    private final ILogicRegister logicRegister;

    // Eager loading singleton
    private static final LogicSystem INSTANCE = new LogicSystem();


    // Constructor
    // Private to prevent client use of 'new' keyword
    private LogicSystem() {
        logicRegister = new LogicRegister();
    }

    public static LogicSystem getInstance() {
        return INSTANCE;
    }

    public ILogicRegister getLogicRegister(){
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
