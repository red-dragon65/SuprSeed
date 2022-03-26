package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface Renderable extends Layerable {

    void draw(RenderHandler renderer);
}
