package dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunner;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineTools;

public class PauseText extends Sprite {

    public PauseText(AssetBundle assetBundle) {
        super(assetBundle);

        // Center the pause text overlay
        float horizontalCenter = EngineTools.getViewPort().getWidth() / 2;
        horizontalCenter -= getWidth() / 2;

        setX(horizontalCenter);

        float verticalCenter = EngineTools.getViewPort().getHeight() / 2;
        verticalCenter -= getHeight() / 2;

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

        if (LoopRunner.loopy.isSoftPause()) {

            renderer.drawSprite(this);
        }
    }
}
