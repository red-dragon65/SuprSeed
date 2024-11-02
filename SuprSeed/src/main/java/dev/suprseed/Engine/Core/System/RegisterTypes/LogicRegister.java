package dev.suprseed.Engine.Core.System.RegisterTypes;

import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;

public interface LogicRegister extends ObjectRegister<LogicRunnable> {

    void update();
}
