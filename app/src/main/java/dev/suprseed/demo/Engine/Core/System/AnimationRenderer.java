package dev.suprseed.demo.Engine.Core.System;

import dev.suprseed.demo.Engine.Core.System.Register.UpdatableRegister;
import dev.suprseed.demo.Engine.Lib.Images.Animator;

import java.util.ArrayList;
import java.util.List;

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
