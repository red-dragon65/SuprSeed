package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.Images.Animator;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.UpdatableRegister;

import java.util.List;

public class AnimationRenderer implements UpdatableRegister<Animator> {


    private final List<Animator> animationQueue;


    // Constructor
    public AnimationRenderer(List<Animator> animatorQueue) {

        this.animationQueue = animatorQueue;
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
