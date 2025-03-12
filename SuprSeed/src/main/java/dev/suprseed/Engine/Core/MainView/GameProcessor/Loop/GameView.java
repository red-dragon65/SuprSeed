package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.EngineTools;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {


    // TODO: REMOVE THIS! Instead, override 'instance state configuration changes'!!!
    protected static boolean initialized = false;
    // Dependencies
    protected LoopRunnable<GameView> loopRunner;
    protected RenderHandler renderer;
    protected RootScene rootScene;
    protected SceneStarter sceneStarter;
    protected InputProcessor inputProcessor;
    protected RefreshHandler refreshHandler;


    // Constructor
    public GameView(Context context, RefreshHandler refreshHandler, LoopRunnable<GameView> loopRunner,
                    RenderHandler renderer, SceneStarter sceneStarter, InputProcessor inputProcessor) {
        super(context);

        // Dependency inject
        this.refreshHandler = refreshHandler;
        refreshHandler.setGameView(this);
        this.loopRunner = loopRunner;
        this.renderer = renderer;

        this.sceneStarter = sceneStarter;
        rootScene = new RootScene() {
        };

        this.inputProcessor = inputProcessor;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        EngineContext.getScreen().setDimensions(h, w);

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

        // Set the renderers canvas
        renderer.setCanvas(canvas);
        refreshHandler.monitorRefreshRate();

        loopRunner.run(this);
    }


    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        // Stop logic and drawing
        loopRunner.setHardPause(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        // Update refresh rate before initializing loop
        refreshHandler.updateRefreshRate();

        // Continue logic and drawing
        loopRunner.setHardPause(false);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        // Continue logic and drawing
        loopRunner.setHardPause(false);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        // Stop/resume logic only (drawing still allowed)
        loopRunner.setSoftPause(!hasWindowFocus);

        // Notify listeners of window change (useful for pausing game)
        EngineTools.getWindowEventRegistry().onFocusChanged(hasWindowFocus);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Process touch input
        // Note: this runs regardless if the game loop is allowed to run
        inputProcessor.processInput(event);


        // Returns whether the event was handled or not
        return true;
    }
}
