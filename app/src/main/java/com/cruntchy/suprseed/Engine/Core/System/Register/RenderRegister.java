package com.cruntchy.suprseed.Engine.Core.System.Register;

import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface RenderRegister<T> extends ObjectRegister<T> {

    void update(RenderHandler handler);
}
