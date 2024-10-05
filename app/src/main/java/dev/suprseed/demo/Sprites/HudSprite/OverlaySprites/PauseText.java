package dev.suprseed.demo.Sprites.HudSprite.OverlaySprites;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopManager;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class PauseText extends Sprite {

    public PauseText(BaseScene parentScene, ImageHandler imageHandler) {
        super(parentScene, imageHandler);

        // Center the pause text overlay
        float horizontalCenter = CanvasData.getInstance().getScaledWidth() / 2;
        horizontalCenter -= imageHandler.getSelectedImageSet().getScaledWidth() / 2;

        setX(horizontalCenter);

        float verticalCenter = CanvasData.getInstance().getScaledHeight() / 2;
        verticalCenter -= imageHandler.getSelectedImageSet().getScaledHeight() / 2;

        setY(verticalCenter);
    }

    @Override
    public void runLogic() {

        // Ignore this
    }

    @Override
    public void draw(RenderHandler renderer) {
        //super.draw(renderer);

        // We only want to draw if soft pause is occurring

        if (LoopManager.loopy.isSoftPause()) {

            renderer.drawSprite(this);
        }
    }
}
