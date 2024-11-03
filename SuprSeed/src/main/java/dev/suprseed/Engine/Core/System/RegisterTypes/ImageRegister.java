package dev.suprseed.Engine.Core.System.RegisterTypes;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.Registerables.RenderableAndLayerable;

public interface ImageRegister extends ObjectRegister<RenderableAndLayerable> {

    void update(RenderHandler handler);

    void syncLayers();
}
