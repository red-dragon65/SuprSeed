package dev.suprseed.demo.Sprites.HudSprite.OverlaySprites;


import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Fonts.FontHolder;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.R;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.HudSprite.HudFontPaintStrategies.GameOverPaintStrategy;
import dev.suprseed.demo.Sprites.HudSprite.HudInput.FullScreenRestartInput;

public class GameOver extends Sprite {

    private final InputListener screenListener;
    private final GameOverData gameOverData;
    private final FontHolder gameOverFont;

    private final int gameOverTextLocY = 100;
    private final int gameOverTextLocX = 20;

    private final int restartTextLocY = 130;
    private final int restartTextLocX = 3;

    private final String gameOverText = "Game Over";
    private final String restartText = "Tap to Restart";


    public GameOver(BaseScene parentScene, GameOverData gameOverData) {
        super(parentScene, null);

        gameOverFont = new FontHolder(R.font.peaberry_base, 12, parentScene.getContext(), new GameOverPaintStrategy());

        // Start hidden until game over occurs
        setDrawable(false);

        this.gameOverData = gameOverData;

        screenListener = new FullScreenRestartInput(this, gameOverData);

        EngineTools.getInputManager().getListenerRegistry().registerObject(screenListener);

        // Set text location
        setX(gameOverTextLocX);
        setY(gameOverTextLocY);
    }

    @Override
    public void runLogic() {

        // Show game over image if game over occurs
        if (gameOverData.isGameOver() && !isDrawable()) {
            setDrawable(true);
        }
    }


    @Override
    public void draw(RenderHandler renderer) {

        // Don't draw the 'image handler!' It is null...
        //super.draw(renderer);

        // Draw the game over text here instead


        // Set the font settings
        gameOverFont.updatePaint(renderer.getPaint());


        // Scale the location then draw text
        setY(gameOverTextLocY);
        setX(gameOverTextLocX);
        float[] output = renderer.getCoordinateHandler().parseLocation(this);
        renderer.getCanvas().drawText(gameOverText, output[0], output[1], renderer.getPaint());


        // Scale the location then draw text
        setY(restartTextLocY);
        setX(restartTextLocX);
        output = renderer.getCoordinateHandler().parseLocation(this);
        renderer.getCanvas().drawText(restartText, output[0], output[1], renderer.getPaint());
    }
}
