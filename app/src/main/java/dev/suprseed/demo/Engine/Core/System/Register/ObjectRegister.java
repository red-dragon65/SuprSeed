package dev.suprseed.demo.Engine.Core.System.Register;

public interface ObjectRegister<T> {

    void registerObject(T object);

    void removeObject(T object);

    void removeAllObjects();
}
