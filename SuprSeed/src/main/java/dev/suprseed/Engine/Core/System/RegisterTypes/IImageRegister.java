package dev.suprseed.Engine.Core.System.RegisterTypes;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface IImageRegister<T> extends IObjectRegister<T> {

    void update(RenderHandler handler);
}
