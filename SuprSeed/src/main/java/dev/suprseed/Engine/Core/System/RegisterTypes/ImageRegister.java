package dev.suprseed.Engine.Core.System.RegisterTypes;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface ImageRegister<T> extends ObjectRegister<T> {

    void update(RenderHandler handler);
}
