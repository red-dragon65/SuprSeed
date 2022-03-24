package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.Images.Animator;

public interface RenderRegister {
    void registerRenderSprite(Renderable sprite);

    void registerAnimationImage(Animator animation);
}
