package dev.suprseed.Engine.Lib.Input;

import dev.suprseed.Engine.Lib.Input.Dispatchers.EventDispatcher;
import dev.suprseed.Engine.Lib.Input.Registers.InputRegister;

public interface InputManager {

    InputRegister<EventDispatcher> getDispatcherRegistry();

    InputRegister<InputListener> getListenerRegistry();
}
