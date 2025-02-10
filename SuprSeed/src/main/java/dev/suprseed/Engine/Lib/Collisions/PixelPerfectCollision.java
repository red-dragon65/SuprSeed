package dev.suprseed.Engine.Lib.Collisions;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineContext;

public class PixelPerfectCollision implements CollisionHandler {

    private static final RectF firstBounds = new RectF();
    private static final RectF secondBounds = new RectF();
    private static final RectF overlapBounds = new RectF();
    private static final RectF firstSubBounds = new RectF();
    private static final RectF secondSubBounds = new RectF();

    private final ImageSubBounds firstSubImage = new ImageSubBounds();
    private final ImageSubBounds secondSubImage = new ImageSubBounds();
    // Dependency injection
    private final CollisionHandler basicCollision;
    private int pixelMaskResolution = 1;

    // Constructor
    public PixelPerfectCollision(CollisionHandler basicCollision, int pixelMaskResolution) {

        // Check user input
        if (pixelMaskResolution < 1) {
            EngineContext.getLogger().logMessage(ErrorType.WARN, "The pixel mask resolution must be equal to or greater than 1! Defaulting to 1.");
            pixelMaskResolution = 1;
        }

        this.pixelMaskResolution = pixelMaskResolution;
        this.basicCollision = basicCollision;
    }

    /*
    what is absolute?
    - the image
    - the screen
    - the pixels

    what is not absolute?
    - the rectangle
    - the viewport
    - the location


    Doing the algo
    - get the x and y
    - upscale to get actual x and y
    - get the actual loaded image size
    - create the actual rectangle
    - actual rectangle now == actual loaded image size + actual xy offset
    - find only the bounds where the rectangles intersect
    - find 0-index based bounds of overlay within each actual rectangle
    - traverse actual loaded image using found overlay bounds
     */
    @Override
    public boolean checkCollision(Sprite one, Sprite two) {

        // Check if basic collision has occurred first
        if (!basicCollision.checkCollision(one, two)) {
            return false;
        }

        // Get the rect for both sprites
        one.getRectF(firstBounds);
        two.getRectF(secondBounds);

        // Upscale to get the actual x and y
        firstBounds.left = EngineContext.getScreen().convertViewPortToCanvas(firstBounds.left);
        firstBounds.top = EngineContext.getScreen().convertViewPortToCanvas(firstBounds.top);
        secondBounds.left = EngineContext.getScreen().convertViewPortToCanvas(secondBounds.left);
        secondBounds.top = EngineContext.getScreen().convertViewPortToCanvas(secondBounds.top);

        // Get the actual rectangle based on the actual loaded image
        firstBounds.right = firstBounds.left + one.getAssetBundle().getSelectedImageSet().getImage().getWidth();
        firstBounds.bottom = firstBounds.top + one.getAssetBundle().getSelectedImageSet().getImage().getHeight();
        secondBounds.right = secondBounds.left + two.getAssetBundle().getSelectedImageSet().getImage().getWidth();
        secondBounds.bottom = secondBounds.top + two.getAssetBundle().getSelectedImageSet().getImage().getHeight();

        // Calculate the overlap area
        overlapBounds.set(firstBounds);
        overlapBounds.intersect(secondBounds.left, secondBounds.top, secondBounds.right, secondBounds.bottom);

        // Calculate the location of the overlap relative to the original rectangle
        // Relative: the overlap area in terms of pixel indices of the image
        firstSubBounds.left = (int) Math.abs(firstBounds.left - overlapBounds.left);
        firstSubBounds.right = (int) Math.abs(firstBounds.left - overlapBounds.right);
        firstSubBounds.top = (int) Math.abs(firstBounds.top - overlapBounds.top);
        firstSubBounds.bottom = (int) Math.abs(firstBounds.top - overlapBounds.bottom);

        secondSubBounds.left = (int) Math.abs(secondBounds.left - overlapBounds.left);
        secondSubBounds.right = (int) Math.abs(secondBounds.left - overlapBounds.right);
        secondSubBounds.top = (int) Math.abs(secondBounds.top - overlapBounds.top);
        secondSubBounds.bottom = (int) Math.abs(secondBounds.top - overlapBounds.bottom);

        // Prepare DTO
        firstSubImage.setBounds(firstSubBounds, one.getAssetBundle().getSelectedImageSet().getImage());
        secondSubImage.setBounds(secondSubBounds, two.getAssetBundle().getSelectedImageSet().getImage());

        // Calculate pixel perfect collision
        return imageSubTraverse(firstSubImage, secondSubImage, pixelMaskResolution);
    }

    private boolean imageSubTraverse(ImageSubBounds firstSubImage, ImageSubBounds secondSubImage, int traverseDensity) {

        int pixel1 = 0;
        int pixel2 = 0;

        // Prime the starting index
        int firstXCount = firstSubImage.getBounds().left;
        int firstYCount = firstSubImage.getBounds().top;
        int secondXCount = secondSubImage.getBounds().left;
        int secondYCount = secondSubImage.getBounds().top;

        int firstXEnding = firstSubImage.getBounds().right;
        int firstYEnding = firstSubImage.getBounds().bottom;
        int secondXEnding = secondSubImage.getBounds().right;
        int secondYEnding = secondSubImage.getBounds().bottom;

        Bitmap firstImage = firstSubImage.getImage();
        Bitmap secondImage = secondSubImage.getImage();

        while (true) {

            if (firstXCount >= firstXEnding || secondXCount >= secondXEnding) {
                firstXCount = (int) firstSubBounds.left;
                secondXCount = (int) secondSubBounds.left;

                firstYCount += traverseDensity;
                secondYCount += traverseDensity;
            }

            if (firstYCount >= firstYEnding || secondYCount >= secondYEnding) {
                return false;
            }


            // Get pixels from both images
            pixel1 = firstImage.getPixel(firstXCount, firstYCount);
            pixel2 = secondImage.getPixel(secondXCount, secondYCount);

            // See if collision has occurred
            if (pixel1 != Color.TRANSPARENT && pixel2 != Color.TRANSPARENT) {

                // Collision has occurred!
                return true;
            }

            firstXCount += traverseDensity;
            secondXCount += traverseDensity;
        }
    }

    private static class ImageSubBounds {

        private Rect subBounds = new Rect();

        private Bitmap image;

        private void setBounds(RectF bounds, Bitmap image) {

            this.image = image;

            // Make sure bounds is within image dimensions

            if (bounds.left < 0) {
                subBounds.left = 0;
            } else {
                subBounds.left = (int) bounds.left;
            }

            if (bounds.right > image.getWidth()) {
                subBounds.right = image.getWidth();
            } else {
                subBounds.right = (int) bounds.right;
            }

            if (bounds.top < 0) {
                subBounds.top = 0;
            } else {
                subBounds.top = (int) bounds.top;
            }

            if (bounds.bottom > image.getHeight()) {
                subBounds.bottom = image.getHeight();
            } else {
                subBounds.bottom = (int) bounds.bottom;
            }

        }

        public Rect getBounds() {
            return subBounds;
        }

        public Bitmap getImage() {
            return image;
        }
    }
}
