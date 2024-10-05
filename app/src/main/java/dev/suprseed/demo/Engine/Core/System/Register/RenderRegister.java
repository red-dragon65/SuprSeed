package dev.suprseed.demo.Engine.Core.System.Register;

import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface RenderRegister<T> extends ObjectRegister<T> {

    void update(RenderHandler handler);
}
