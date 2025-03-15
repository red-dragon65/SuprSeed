package dev.suprseed.demo.SplashScene.Sprites;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.demo.Utils.AlphaController;

public class FadeoutSprite extends Sprite {

    private AlphaController alphaPaint;

    public FadeoutSprite(AssetBundle assetBundle, AlphaController alphaPaint) {
        super(assetBundle);
        this.alphaPaint = alphaPaint;
    }

    @Override
    public void runLogic() {

    }

    @Override
    public void draw(RenderHandler renderer) {
        //super.draw(renderer);

        renderer.getCanvas().drawARGB(alphaPaint.getCurrentAlpha(), 0, 0, 0);

        alphaPaint.update();
    }
}
