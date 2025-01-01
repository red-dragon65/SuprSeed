package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

public class VelocityScaler {
    private static float velocityScaler = 1;

    public static float getVelocityScaler() {
        return velocityScaler;
    }

    public static void setVelocityScaler(float scaleRatio) {
        velocityScaler = scaleRatio;
    }
}
