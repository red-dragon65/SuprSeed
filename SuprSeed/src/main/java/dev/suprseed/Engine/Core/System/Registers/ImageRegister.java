package dev.suprseed.Engine.Core.System.Registers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.RegisterTypes.IImageRegister;
import dev.suprseed.Engine.Core.System.Registerables.ILayerable;
import dev.suprseed.Engine.Core.System.Registerables.IRenderableAndILayerable;

public class ImageRegister implements IImageRegister<IRenderableAndILayerable> {


    private final List<IRenderableAndILayerable> renderQueue;
    private final Comparator<ILayerable> layerableComparator;
    private boolean isLayerSynced = false;


    // Constructor
    public ImageRegister(Comparator<ILayerable> layerableComparator) {

        this.renderQueue = new ArrayList<>();
        this.layerableComparator = layerableComparator;
    }


    @Override
    public void update(RenderHandler renderer) {

        // Re-sort the sprite list if necessary
        if (!isLayerSynced) {

            renderQueue.sort(layerableComparator);
            isLayerSynced = true;
        }

        // Draw the sprites
        for (IRenderableAndILayerable item : renderQueue) {

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
    public void registerObject(IRenderableAndILayerable object) {

        renderQueue.add(object);
        isLayerSynced = false;
    }

    @Override
    public void removeObject(IRenderableAndILayerable object) {

        renderQueue.remove(object);
    }


    @Override
    public void removeAllObjects() {

        renderQueue.clear();
    }
}
