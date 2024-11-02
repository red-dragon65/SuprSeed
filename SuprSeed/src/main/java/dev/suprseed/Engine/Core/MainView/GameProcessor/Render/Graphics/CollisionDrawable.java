package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.Registerables.RenderableAndLayerable;

public interface CollisionDrawable extends RenderableAndLayerable {

    void setColor(int rectColor);

    void addOverlay(Sprite sprite);

    boolean isEnabled();

    void setEnabled(boolean isEnabled);
}
