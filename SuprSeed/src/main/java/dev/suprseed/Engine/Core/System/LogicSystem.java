package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.System.RegisterTypes.LogicRegister;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;
import dev.suprseed.Engine.Core.System.Registers.LogicRegistry;

public class LogicSystem implements LogicRunnable {

    private final LogicRegister logicRegister;


    // Constructor
    // Private to prevent client use of 'new' keyword
    public LogicSystem() {
        logicRegister = new LogicRegistry();
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
