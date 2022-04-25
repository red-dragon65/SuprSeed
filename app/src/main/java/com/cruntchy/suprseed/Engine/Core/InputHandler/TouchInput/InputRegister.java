package com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput;

import com.cruntchy.suprseed.Engine.Core.System.Register.ObjectRegister;

import java.util.List;

public interface InputRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();
}
