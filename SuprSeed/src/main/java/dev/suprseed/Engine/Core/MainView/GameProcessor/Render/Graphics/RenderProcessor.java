package dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class RenderProcessor implements RenderHandler {

    private final Paint paint = new Paint();
    // Dependencies
    private final CoordinateHandler coordinateHandler;
    // The hardware accelerated canvas provided by a view
    private Canvas canvas;
    private CollisionDiagnosable collisionDiagnoser;


    // Constructor
    public RenderProcessor(CoordinateHandler coordinateHandler, CollisionDiagnosable collisionDiagnoser) {

        // Dependency injection
        this.coordinateHandler = coordinateHandler;
        this.collisionDiagnoser = collisionDiagnoser;
    }

    @Override
    public void drawSprite(Sprite sprite) {

        // Show warning if canvas is not set
        if (this.canvas == null) {

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "The canvas has not been initialized!");

            return;
        }

        if (CanvasData.getInstance().getOriginalWidth() == 0 || CanvasData.getInstance().getOriginalHeight() == 0) {

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "The canvas dimensions have not been initialized!!");

            return;
        }


        // Only process sprite overlays if the diagnoser is enabled
        if (collisionDiagnoser.isEnabled()) {

            collisionDiagnoser.addOverlay(sprite);
        }

        // Actually draw sprite
        //if(sprite.isActive() && sprite.isDrawable()){ // IGNORE THIS! RENDERSYSTEM HANDLES DRAWABILITY

        // Get drawing location of sprite
        float[] finalLoc = coordinateHandler.parseLocation(sprite);


        // Make sure the image exists
        if (sprite.getAssetBundle() != null) {

            // Draw the sprite at the final location
            canvas.drawBitmap(
                    sprite.getAssetBundle().getSelectedImageSet().getImage(),
                    finalLoc[0],
                    finalLoc[1],
                    paint
            );
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
