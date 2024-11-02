package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.Registerables.RenderableAndLayerable;

public interface CollisionDiagnosable extends RenderableAndLayerable {

    void addOverlay(Sprite sprite);

    boolean isEnabled();

    void setEnabled(boolean isEnabled);
}
