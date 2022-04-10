package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ObjectRegister;

import java.util.List;

public interface InputRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();
}
