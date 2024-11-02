package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.Engine.SceneStarter;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {


    // TODO: REMOVE THIS! Instead, override 'instance state configuration changes'!!!
    protected static boolean initialized = false;
    // Dependencies
    protected LoopRunnable<GameView> loopRunner;
    protected RenderHandler renderer;
    protected RootScene rootScene;
    protected SceneStarter sceneStarter;
    protected InputHandler inputHandler;
    protected Context context;


    // Constructor
    public GameView(Context context, LoopRunnable<GameView> loopRunner,
                    RenderHandler renderer, SceneStarter sceneStarter, InputHandler inputHandler) {
        super(context);

        this.context = context;

        // Dependency inject
        this.loopRunner = loopRunner;
        this.renderer = renderer;

        this.sceneStarter = sceneStarter;
        rootScene = RootScene.getInstance(context);

        this.inputHandler = inputHandler;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        CanvasData.getInstance().setDimensions(h, w);

        // Initialize the assets
        // Initialize static game objects
        // Initialize a new game loop
        if (!initialized) {
            sceneStarter.initStartingState(rootScene);
            initialized = true;
        }


        // Force onDraw call
        setWillNotDraw(false);

        // Tell SurfaceView SurfaceHolder is being used
        getHolder().addCallback(this);

        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: Verify that this will actually work
        // Set the renderers canvas
        renderer.setCanvas(canvas);

        /*
        Game Renderer
        --------------
        Set refresh speed
        - Only for higher APIs
        - Can be set to 60fps or 120fps
        - Handles asset scaling???
        - Handles drawing

        Physics Handler
        ----------------
        Set logic rate
        - Can be set to 30, 60, 120, or 240 ticks
        - Scales drawing location (makes setting sprite location the same despite number of ticks)
        - Converts coordinate rotation


        This class
        -----------
        Call users physics
        - GameLoop.logicLoop();

        Call user drawing
        - GameLoop.drawingLoop();

         */


        loopRunner.run(this);
    }


    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        // Stop logic and drawing
        loopRunner.setHardPause(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        // Update the loop config
        loopRunner.initLoop(this);

        // Continue drawing
        loopRunner.setHardPause(false);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        // Continue drawing
        loopRunner.setHardPause(false);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        // Pause game drawing (in game pause)
        if (!hasWindowFocus) {

            // Stop logic only (drawing still allowed)
            loopRunner.setSoftPause(true);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Process touch input
        // Note: this runs regardless if the game loop is allowed to run
        inputHandler.processInput(event);


        // Returns whether the event was handled or not
        return true;
    }
}
