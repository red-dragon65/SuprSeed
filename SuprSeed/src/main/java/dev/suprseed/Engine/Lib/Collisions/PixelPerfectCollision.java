package dev.suprseed.Engine.Lib.Collisions;

import android.graphics.Color;
import android.graphics.RectF;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Screen;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class PixelPerfectCollision implements CollisionHandler {

    private static final RectF first = new RectF();
    private static final RectF second = new RectF();
    private static final RectF temp = new RectF();
    // Dependency injection
    private final CollisionHandler basicCollision;
    private int pixelMaskResolution = 1;


    // Constructor
    public PixelPerfectCollision(CollisionHandler basicCollision, int pixelMaskResolution) {

        // Check user input
        if (pixelMaskResolution < 1) {
            CentralLogger.getInstance().logMessage(ErrorType.WARN, "The pixel mask resolution must be equal to or greater than 1! Defaulting to 1.");
            pixelMaskResolution = 1;
        }

        this.pixelMaskResolution = pixelMaskResolution;
        this.basicCollision = basicCollision;
    }


    @Override
    public boolean checkCollision(Sprite one, Sprite two) {

        // Check if basic collision has occurred first
        if (!basicCollision.checkCollision(one, two)) {
            return false;
        }

        // Get the rect for both sprites
        one.getRectF(first);
        two.getRectF(second);

        // Calculate the overlap area for both sprites
        temp.set(first);
        first.intersect(second.left, second.top, second.right, second.bottom);
        second.intersect(temp.left, temp.top, temp.right, temp.bottom);

        // TODO: VERIFY THAT THIS IS WORKING AS EXPECTED!
        // Offset the overlap area so it corresponds to sprite pixel indices
        first.left -= Screen.getInstance().convertViewPortToCanvas(one.getX());
        first.right -= Screen.getInstance().convertViewPortToCanvas(one.getX());
        first.top -= Screen.getInstance().convertViewPortToCanvas(one.getY());
        first.bottom -= Screen.getInstance().convertViewPortToCanvas(one.getY());

        second.left -= Screen.getInstance().convertViewPortToCanvas(two.getX());
        second.right -= Screen.getInstance().convertViewPortToCanvas(two.getX());
        second.top -= Screen.getInstance().convertViewPortToCanvas(two.getY());
        second.bottom -= Screen.getInstance().convertViewPortToCanvas(two.getY());


        // Calculate if a collision has occurred
        for (int xStarting1 = (int) PixelPerfectCollision.first.left, xEnding1 = (int) PixelPerfectCollision.first.right,
             yStarting1 = (int) PixelPerfectCollision.first.top, yEnding1 = (int) PixelPerfectCollision.first.bottom,
             xStarting2 = (int) PixelPerfectCollision.second.left, xEnding2 = (int) PixelPerfectCollision.second.right,
             yStarting2 = (int) PixelPerfectCollision.second.top, yEnding2 = (int) PixelPerfectCollision.second.bottom;
             xStarting1 < xEnding1 && yStarting1 < yEnding1 && // Image 1 bounds
                     xStarting2 < xEnding2 && yStarting2 < yEnding2; // Image 2 bounds
             xStarting1 += pixelMaskResolution, yStarting1 += pixelMaskResolution, // Image 1 indices
                     xStarting2 += pixelMaskResolution, yStarting2 += pixelMaskResolution // Image 2 indices
        ) {

            // Get pixels from both images
            int pixel1 = one.getAssetBundle().getSelectedImageSet().getImage().getPixel(xStarting1, yStarting1);
            int pixel2 = two.getAssetBundle().getSelectedImageSet().getImage().getPixel(xStarting2, yStarting2);

            // See if collision has occurred
            if (pixel1 != Color.TRANSPARENT && pixel2 != Color.TRANSPARENT) {

                // Collision has occurred!
                return true;
            }

        }

        // No collision has occurred...
        return false;
    }
}
