package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Player;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.EngineTools;

public class RenderProcessor implements RenderHandler {

    private final Paint paint = new Paint();
    // Dependencies
    private final CoordinateHandler coordinateHandler;
    // The hardware accelerated canvas provided by a view
    private Canvas canvas;


    // Constructor
    public RenderProcessor(CoordinateHandler coordinateHandler) {

        // Dependency injection
        this.coordinateHandler = coordinateHandler;
    }

    @Override
    public void drawSprite(Sprite sprite) {

        // Show warning if canvas is not set
        if (this.canvas == null) {

            EngineContext.getLogger().logMessage(ErrorType.WARN, "The canvas has not been initialized!");

            return;
        }

        if (EngineContext.getScreen().getWidth() == 0 || EngineContext.getScreen().getHeight() == 0) {

            EngineContext.getLogger().logMessage(ErrorType.WARN, "The canvas dimensions have not been initialized!!");

            return;
        }


        // Only process sprite overlays if the diagnoser is enabled
        if (EngineTools.getCollisionDrawer().isEnabled()) {

            EngineTools.getCollisionDrawer().addOverlay(sprite);
        }

        // Actually draw sprite
        //if(sprite.isActive() && sprite.isDrawable()){ // IGNORE THIS! RENDERSYSTEM HANDLES DRAWABILITY

        // Get drawing location of sprite
        float[] finalLoc = coordinateHandler.parseLocation(sprite);


        // Make sure the sprite has an asset
        if (sprite.getAssetBundle() != null) {

            Bitmap image;

            // See if the asset has a animation player
            if (sprite.getAssetBundle().getSelectedAsset().isPlayerInitialized()) {

                // Get the player
                Optional<Player> player = sprite.getAssetBundle().getSelectedAsset().getPlayer();

                if (!player.isPresent()) {

                    // This should never happen!
                    throw new RuntimeException("The sprites player is null!");
                }

                // Get the image index
                int index = player.get().getCurrentFrameIndex();

                // Get the image if it exists
                if (sprite.getAssetBundle().getSelectedAsset().getImageSet().getIndexedImage(index).isPresent()) {
                    image = sprite.getAssetBundle().getSelectedAsset().getImageSet().getIndexedImage(index).get();
                } else {
                    image = null;
                }

            } else {
                image = sprite.getAssetBundle().getSelectedAsset().getImageSet().getImage();
            }

            if (image != null) {

                // Draw the sprite at the final location
                canvas.drawBitmap(
                        image,
                        finalLoc[0],
                        finalLoc[1],
                        paint
                );
            }
        }

    }

    @Override
    public CoordinateHandler getCoordinateHandler() {
        return coordinateHandler;
    }

    @Override
    public Paint getPaint() {
        return paint;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void setCanvas(Canvas canvas) {

        // Get the latest canvas from onDraw
        this.canvas = canvas;
    }

}
