package dev.suprseed.Engine.Core.System;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.Register.UpdatableRegister;
import dev.suprseed.Engine.Lib.Images.Animator;

public class AnimationRenderer implements UpdatableRegister<Animator> {


    private final List<Animator> animationQueue;


    // Constructor
    public AnimationRenderer() {

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
