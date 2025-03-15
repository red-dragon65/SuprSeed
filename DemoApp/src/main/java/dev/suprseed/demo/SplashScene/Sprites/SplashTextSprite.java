package dev.suprseed.demo.SplashScene.Sprites;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.demo.Utils.AlphaController;

public class SplashTextSprite extends Sprite {

    private AlphaController alphaPaint;

    public SplashTextSprite(AssetBundle assetBundle, AlphaController alphaPaint) {
        super(assetBundle);

        this.alphaPaint = alphaPaint;

        setX(50 - (getWidth() / 2));
        setY(95);
    }

    @Override
    public void runLogic() {

    }

    @Override
    public void draw(RenderHandler renderer) {

        // Prevent the system from drawing this sprite
        // We will handle drawing this sprite using a custom alpha channel
        //super.draw(renderer);

        // Draw our image with opacity
        float[] output = renderer.getCoordinateHandler().parseLocation(this);
        renderer.getCanvas().drawBitmap(assetBundle.getSelectedAsset().getImageSet().getImage(), output[0], output[1], alphaPaint.getCurrentAlphaPaint());

        alphaPaint.update();
    }
}
