package dev.suprseed.Engine.Core.System.RegisterTypes;

public interface ObjectRegister<T> {

    void registerObject(T object);

    void removeObject(T object);

    void removeAllObjects();
}
