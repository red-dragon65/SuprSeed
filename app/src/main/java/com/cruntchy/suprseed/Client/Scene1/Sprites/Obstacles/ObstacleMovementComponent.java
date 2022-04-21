package com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

import java.util.List;

public class ObstacleMovementComponent implements Movable {

    private List<Sprite> obstacles;
    private BounceData heroBounceData;
    private Sprite hero;


    public ObstacleMovementComponent(BounceData heroBounceData, List<Sprite> obstacles, Sprite hero){

        this.obstacles = obstacles;
        this.heroBounceData = heroBounceData;
        this.hero = hero;
    }


    @Override
    public void move() {

        for(Sprite s : obstacles){

            s.setyVel(-heroBounceData.getBounceValue());
        }
    }
}
