package dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase;

import dev.suprseed.demo.Engine.Lib.Images.SpriteImage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ImageHandler {

    private Map<String, SpriteImage> spriteImage;
    private String selectedImage;


    // Constructor that takes one sprite image set
    public ImageHandler(String name, SpriteImage spriteImageSet) {

        if (name != null && spriteImageSet != null) {

            spriteImage = new HashMap<>();
            spriteImage.put(name, spriteImageSet);

            selectedImage = getFirstImageSetId();
        }

    }

    // Constructor that takes multiple sprite image sets
    public ImageHandler(Map<String, SpriteImage> sprites) {

        if (sprites != null) {

            this.spriteImage = sprites;

            selectedImage = getFirstImageSetId();
        }

    }


    public SpriteImage getSpriteImageSetById(String imageName) {

        verifySpriteList();

        if (spriteImage.containsKey(imageName)) {

            return spriteImage.get(imageName);
        }

        throw new NullPointerException("Image with key: '" + imageName + "' does not exist!");
    }


    public SpriteImage getSelectedImageSet() {

        verifySpriteList();

        return spriteImage.get(selectedImage);
    }

    public void setSelectedImageSet(String selected) {

        // Verify id exists
        if (spriteImage.containsKey(selected)) {

            selectedImage = selected;
            return;
        }

        throw new NullPointerException("Image with key: '" + selected + "' does not exist!");
    }


    private String getFirstImageSetId() {

        verifySpriteList();

        // Get the first key
        Optional<String> firstKey = spriteImage.keySet().stream().findFirst();

        // Verify key exists
        if (firstKey.isPresent()) {

            return firstKey.get();
        }

        throw new NullPointerException("Could not get image id! Verify sprite list is initialized!");
    }


    public void addImageSet(String name, SpriteImage spriteSet) {

        verifySpriteList();

        spriteImage.put(name, spriteSet);
    }

    public void verifySpriteList() {

        if (spriteImage == null || spriteImage.size() <= 0) {

            throw new NullPointerException("No sprites are in the image set!");
        }
    }

}
