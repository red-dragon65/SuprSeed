package dev.suprseed.Engine.Core.System.Register;

import java.util.List;

public interface ListRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();
}
