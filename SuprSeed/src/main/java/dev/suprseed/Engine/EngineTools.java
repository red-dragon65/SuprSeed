package dev.suprseed.Engine;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopControllable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.VelocityScaler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.WindowEventRegistry;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.Camera;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.CollisionDrawable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.ViewPort;
import dev.suprseed.Engine.Lib.Input.InputManager;

public class EngineTools {

    // TODO: use interface, not definition
    private static Camera cameraInstance = null;
    private static ViewPort viewPortInstance = null;
    private static VelocityScaler velocityScalerInstance;

    private static InputManager inputManagerInstance = null;
    private static CollisionDrawable collisionDrawerInstance = null;

    private static WindowEventRegistry windowEventRegistryInstance = null;

    private static LoopControllable loopControllerInstance = null;

    private static void logIfPresent(Object oldInstance, Object newInstance) {
        if (oldInstance != null) {
            EngineContext.getLogger().logMessage(ErrorType.WARN,
                    "WARNING! Changing services during runtime may cause engine instability!" +
                            "The " + oldInstance.getClass().getName() + " instance was already set!" + "\n" +
                            "The " + oldInstance.getClass().getName() + " has been replaced by " + newInstance.getClass().getName());
        }
    }

    public static Camera getGlobalCamera() {
        return cameraInstance;
    }

    public static void setGlobalCamera(Camera camera) {
        logIfPresent(cameraInstance, camera);
        cameraInstance = camera;
    }

    public static ViewPort getViewPort() {
        return viewPortInstance;
    }

    public static void setViewPort(ViewPort viewPort) {
        logIfPresent(viewPortInstance, viewPort);
        viewPortInstance = viewPort;
    }

    public static VelocityScaler getVelocityScaler() {
        return velocityScalerInstance;
    }

    public static void setVelocityScaler(VelocityScaler velocityScaler) {
        logIfPresent(velocityScalerInstance, velocityScaler);
        velocityScalerInstance = velocityScaler;
    }

    public static InputManager getInputManager() {
        return inputManagerInstance;
    }

    public static void setInputManager(InputManager inputHandler) {
        logIfPresent(inputManagerInstance, inputHandler);
        inputManagerInstance = inputHandler;
    }

    public static CollisionDrawable getCollisionDrawer() {
        return collisionDrawerInstance;
    }

    public static void setCollisionDrawer(CollisionDrawable collisionDrawer) {
        logIfPresent(collisionDrawerInstance, collisionDrawer);
        collisionDrawerInstance = collisionDrawer;
    }

    public static WindowEventRegistry getWindowEventRegistry() {
        return windowEventRegistryInstance;
    }

    public static void setWindowEventRegistry(WindowEventRegistry windowEventRegistry) {
        logIfPresent(windowEventRegistryInstance, windowEventRegistry);
        windowEventRegistryInstance = windowEventRegistry;
    }

    public static LoopControllable getLoopController() {
        return loopControllerInstance;
    }

    public static void setLoopController(LoopControllable loopController) {
        logIfPresent(loopControllerInstance, loopController);
        loopControllerInstance = loopController;
    }
}
