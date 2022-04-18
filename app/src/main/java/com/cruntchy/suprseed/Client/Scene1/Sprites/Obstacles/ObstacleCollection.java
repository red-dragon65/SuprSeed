package com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

import java.util.ArrayList;

public class ObstacleCollection {


    /*
    Hold an array of sprites
    Hold an array of each behavior type (picture, movement, etc)
    Hold an array of pattern generation types

     */


    private ArrayList<Sprite> obstacleSprites;
    private ArrayList<ImageHandler> obstacleImages;


    public ObstacleCollection(){

        obstacleSprites = new ArrayList<>();
        obstacleImages = new ArrayList<>();


        // Add obstacle sprites here



        /*
        Register for logic and rendering with parent view

        Initialize default list of sprites

        Create logic code
        -> Scroll if hero is moving down
        -> Check for collisions
        -> Generate new obstacles at bottom of screen
            -> Update image of sprite
            -> Update location of sprite


        obstacleMovementComponent.scroll(obstacleSprites);
        obstacleCollisionComponent.collide(obstacleSprites, hero);
        obstacleSpawner.generate(obstacleSprites);

         */
    }

}
