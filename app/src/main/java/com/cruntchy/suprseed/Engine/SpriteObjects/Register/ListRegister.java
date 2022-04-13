package com.cruntchy.suprseed.Engine.SpriteObjects.Register;

import java.util.List;

public interface ListRegister<T> extends ObjectRegister<T>{

    List<T> getRegisterList();
}
