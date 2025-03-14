package dev.suprseed.demo.GamePlayScene.EntityScene.Obstacles.ObstacleComponents;

import java.util.List;

import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.demo.GamePlayScene.SharedData.BounceData;

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
