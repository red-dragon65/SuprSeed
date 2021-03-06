package com.cruntchy.suprseed.Client.DemoScene.Sprites.Obstacles.ObstacleComponents;

import com.cruntchy.suprseed.Client.DemoScene.SharedData.BounceData;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

import java.util.List;

public class ObstacleMovementComponent implements Component {

    private final List<Sprite> obstacles;
    private final BounceData heroBounceData;
    private final Sprite hero;


    public ObstacleMovementComponent(BounceData heroBounceData, List<Sprite> obstacles, Sprite hero) {

        this.obstacles = obstacles;
        this.heroBounceData = heroBounceData;
        this.hero = hero;
    }


    @Override
    public void update() {

        for (Sprite s : obstacles) {

            s.setyVel(-heroBounceData.getBounceValue());
        }
    }
}
