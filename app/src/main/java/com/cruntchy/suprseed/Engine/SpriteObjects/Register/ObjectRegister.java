package com.cruntchy.suprseed.Engine.SpriteObjects.Register;

public interface ObjectRegister<T> {

    void registerObject(T object);

    void removeObject(T object);

    void removeAllObjects();
}
