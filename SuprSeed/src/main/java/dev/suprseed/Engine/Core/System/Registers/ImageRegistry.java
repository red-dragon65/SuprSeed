package dev.suprseed.Engine.Core.System.Registers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.RegisterTypes.ImageRegister;
import dev.suprseed.Engine.Core.System.Registerables.Layerable;
import dev.suprseed.Engine.Core.System.Registerables.RenderableAndLayerable;

public class ImageRegistry implements ImageRegister {

    private final List<RenderableAndLayerable> renderQueue;
    private final LayerSyncer<RenderableAndLayerable> layerSyncer;


    // Constructor
    public ImageRegistry(Comparator<Layerable> layerableComparator) {

        this.renderQueue = new ArrayList<>();
        this.layerSyncer = new LayerSyncer<>(layerableComparator);
    }

    @Override
    public void update(RenderHandler renderer) {

        // Re-sort the sprite list if necessary
        syncLayers();

        // Draw the sprites
        for (RenderableAndLayerable item : renderQueue) {

            if (item.isDrawable()) {
                item.draw(renderer);
            }

            // Ignore this old code
            // This is not the desired behavior
            // This does not allow the user to override the drawing behavior
            //renderer.drawSprite(s);
        }
    }

    @Override
    public void syncLayers() {
        layerSyncer.syncLayers(renderQueue);
    }


    @Override
    public void registerObject(RenderableAndLayerable object) {

        renderQueue.add(object);

        syncLayers();
    }

    @Override
    public void removeObject(RenderableAndLayerable object) {

        renderQueue.remove(object);
    }


    @Override
    public void removeAllObjects() {

        renderQueue.clear();
    }
}
