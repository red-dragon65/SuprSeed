package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.suprseed.Engine.Lib.Images.SpriteImage;

public class AssetBundle {

    private List<SpriteImage> spriteImage;
    private String selectedImage;


    // Constructor that takes one sprite image set
    public AssetBundle(SpriteImage spriteImageSet) {

        if (spriteImageSet != null) {

            spriteImage = new ArrayList<>();
            spriteImage.add(spriteImageSet);

            selectedImage = getFirstImageSetId();
        }

    }

    // Constructor that takes multiple sprite image sets
    public AssetBundle(List<SpriteImage> sprites) {

        if (sprites != null) {

            this.spriteImage = sprites;

            selectedImage = getFirstImageSetId();
        }

    }

    public int getNumAssets() {
        return spriteImage.size();
    }

    public List<String> getAllIds() {

        return spriteImage.stream().map(SpriteImage::getTag).collect(Collectors.toList());

    }

    public SpriteImage getSpriteImageSetById(String imageName) {

        verifySpriteList();

        try {

            return spriteImage.stream().filter(s -> s.getTag().equals(imageName)).findFirst().orElseThrow();
        } catch (Exception e) {

            throw new NullPointerException("Image with key: '" + imageName + "' does not exist!");
        }
    }


    public SpriteImage getSelectedImageSet() {

        verifySpriteList();

        return spriteImage.stream().filter(s -> s.getTag().equals(selectedImage)).findFirst().orElseThrow();
    }

    public void setSelectedImageSet(String selected) {

        // Verify id exists
        if (spriteImage.stream().anyMatch(s -> s.getTag().equals(selected))) {
            selectedImage = selected;
            return;
        }

        throw new NullPointerException("Image with key: '" + selected + "' does not exist!");
    }


    private String getFirstImageSetId() {

        verifySpriteList();

        // Get the first key
        Optional<SpriteImage> firstKey = spriteImage.stream().findFirst();

        // Verify key exists
        if (firstKey.isPresent()) {

            return firstKey.get().getTag();
        }

        throw new NullPointerException("Could not get image id! Verify sprite list is initialized!");
    }


    public void addImageSet(SpriteImage spriteSet) {

        verifySpriteList();

        spriteImage.add(spriteSet);
    }

    public void verifySpriteList() {

        if (spriteImage == null || spriteImage.size() <= 0) {

            throw new NullPointerException("No sprites are in the image set!");
        }
    }

}
