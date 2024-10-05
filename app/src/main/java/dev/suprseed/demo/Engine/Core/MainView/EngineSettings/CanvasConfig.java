package dev.suprseed.demo.Engine.Core.MainView.EngineSettings;

import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationHandler;

public class CanvasConfig extends BaseConfig<LocationHandler> {


    // Enforce only one setting across instance usages
    private static boolean isTopLeft;


    // Constructor
    public CanvasConfig(boolean isTopLeft) {

        CanvasConfig.isTopLeft = isTopLeft;

        settings.add(new Configurable<LocationHandler>() {

            @Override
            public void applySettings(LocationHandler inputObject) {

                inputObject.setTopLeftOrigin(CanvasConfig.isTopLeft);
            }

            @Override
            public boolean active() {
                return CanvasConfig.isTopLeft;
            }

            @Override
            public String getId() {
                return "origin";
            }
        });
    }

}
