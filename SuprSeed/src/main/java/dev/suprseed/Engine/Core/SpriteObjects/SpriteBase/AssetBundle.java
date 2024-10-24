package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Lib.Images.PlaceHolder;
import dev.suprseed.Engine.Lib.Images.SpriteImage;

public class AssetBundle {

    private List<SpriteImage> imageSet;
    private String selectedImage;


    // Constructor that takes one sprite image set
    public AssetBundle(@NonNull SpriteImage spriteImageSet) {

        imageSet = new ArrayList<>();
        imageSet.add(spriteImageSet);

        selectedImage = imageSet.get(0).getTag();
    }

    // Constructor that takes multiple sprite image sets
    public AssetBundle(@NonNull List<SpriteImage> sprites) {

        this.imageSet = sprites;

        selectedImage = imageSet.get(0).getTag();
    }

    public int getNumAssets() {
        return imageSet.size();
    }

    public List<String> getAllIds() {

        return imageSet.stream().map(SpriteImage::getTag).collect(Collectors.toList());
    }

    public SpriteImage getSpriteImageSetById(String imageName) {

        Optional<SpriteImage> result = imageSet.stream().filter(s -> s.getTag().equals(imageName)).findFirst();

        if (result.isPresent()) {
            return result.get();
        }

        CentralLogger.getInstance().logMessage(ErrorType.ERROR, "Image with key: '" + imageName + "' does not exist in the AssetBundle!");

        /*
        There is no reason for this to ever throw for the end user
        This can only throw if the PlaceHolder has been messed with in other parts of the code base
         */
        return imageSet.stream().filter(s -> s.getTag().equals(PlaceHolder.TAG.PLACEHOLDER.toString())).findFirst().orElseThrow();
    }


    public SpriteImage getSelectedImageSet() {

        /*
        This should always work for the end user
        The constructors will throw an exception if the spriteImage tag can not be set
        The setSelectedImageSet() method will be thrown if the provided string is invalid
         */
        return imageSet.stream().filter(s -> s.getTag().equals(selectedImage)).findFirst().orElseThrow();
    }

    public boolean trySelectingImageSet(String selected) {

        // Verify id exists
        if (imageSet.stream().anyMatch(s -> s.getTag().equals(selected))) {
            selectedImage = selected;
            return true;
        }

        selectedImage = PlaceHolder.TAG.PLACEHOLDER.toString();

        String message = "Image with key: '" + selected + "' does not exist in the AssetBundle!";
        CentralLogger.getInstance().logMessage(ErrorType.ERROR, message);

        return false;
    }

    public void addImageSet(@NonNull SpriteImage spriteSet) {

        imageSet.add(spriteSet);
    }
}
