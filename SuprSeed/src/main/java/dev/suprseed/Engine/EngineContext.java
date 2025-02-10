package dev.suprseed.Engine;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.ErrorLogger.Logable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.VelocityScaler;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Screen;
import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class EngineContext {

    //TODO: Use interface, not definition
    private static CentralLogger centralLoggerInstance;
    private static VelocityScaler velocityScalerInstance;
    private static Screen screenInstance;
    private static LogicSystem logicSystemInstance;
    private static RenderSystem renderSystemInstance;


    private static void logIfPresent(Object oldInstance, Object newInstance) {
        if (oldInstance != null) {
            EngineContext.getLogger().logMessage(ErrorType.WARN,
                    "WARNING! Changing services during runtime may cause engine instability!" +
                            "The " + oldInstance.getClass().getName() + " instance was already set!" + "\n" +
                            "The " + oldInstance.getClass().getName() + " has been replaced by " + newInstance.getClass().getName());
        }
    }

    public static Logable getLogger() {
        return centralLoggerInstance;
    }

    public static void setCentralLogger(CentralLogger centralLogger) {
        logIfPresent(centralLoggerInstance, centralLogger);
        centralLoggerInstance = centralLogger;
    }

    public static LogicSystem getLogicSystem() {
        return logicSystemInstance;
    }

    public static void setLogicSystem(LogicSystem logicSystem) {
        logIfPresent(logicSystemInstance, logicSystem);
        logicSystemInstance = logicSystem;
    }

    public static VelocityScaler getVelocityScaler() {
        return velocityScalerInstance;
    }

    public static void setVelocityScaler(VelocityScaler velocityScaler) {
        logIfPresent(velocityScalerInstance, velocityScaler);
        velocityScalerInstance = velocityScaler;
    }

    public static Screen getScreen() {
        return screenInstance;
    }

    public static void setScreen(Screen screen) {
        logIfPresent(screenInstance, screen);
        screenInstance = screen;
    }

    public static RenderSystem getRenderSystem() {
        return renderSystemInstance;
    }

    public static void setRenderSystem(RenderSystem renderSystem) {
        logIfPresent(renderSystemInstance, renderSystem);
        renderSystemInstance = renderSystem;
    }
}
