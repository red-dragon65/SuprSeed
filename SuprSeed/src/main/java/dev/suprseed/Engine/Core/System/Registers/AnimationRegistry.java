package dev.suprseed.Engine.Core.System.Registers;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Animator;
import dev.suprseed.Engine.Core.System.RegisterTypes.AnimationRegister;

public class AnimationRegistry implements AnimationRegister {


    private final List<Animator> animationQueue;


    // Constructor
    public AnimationRegistry() {

        this.animationQueue = new ArrayList<>();
    }


    @Override
    public void update() {

        // Update all animations to the next frame
        for (Animator ani : animationQueue) {
            ani.generateNextFrame();
        }
    }


    @Override
    public void registerObject(Animator object) {

        animationQueue.add(object);
    }

    @Override
    public void removeObject(Animator object) {

        animationQueue.remove(object);
    }

    @Override
    public void removeAllObjects() {

        animationQueue.clear();
    }
}
