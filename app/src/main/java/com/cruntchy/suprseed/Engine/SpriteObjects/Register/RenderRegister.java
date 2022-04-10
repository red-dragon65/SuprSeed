package com.cruntchy.suprseed.Engine.SpriteObjects.Register;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface RenderRegister<T> extends ObjectRegister<T> {

    void update(RenderHandler handler);
}
