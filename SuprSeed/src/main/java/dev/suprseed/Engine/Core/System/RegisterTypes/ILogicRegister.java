package dev.suprseed.Engine.Core.System.RegisterTypes;

import dev.suprseed.Engine.Core.System.Registerables.ILogicRunnable;

public interface ILogicRegister extends IObjectRegister<ILogicRunnable>{

    void update();
}
