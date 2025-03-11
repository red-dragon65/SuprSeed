package dev.suprseed.demo.Subscenes;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.Scenes.SceneStrategy.SceneHardChange;
import dev.suprseed.Engine.Core.Scenes.SceneStrategy.ScenePreemptLoad;
import dev.suprseed.demo.Subscenes.GamePlay.GamePlayScene;
import dev.suprseed.demo.Subscenes.LoadingScreen.GameLoadSpinner;
import dev.suprseed.demo.Subscenes.Menu.MenuScene;
import dev.suprseed.demo.Subscenes.SplashScreen.SplashScene;

public class MainScene extends SceneManager {

    private SceneHardChange hardSwapper;
    private ChangeSceneRequestDTO changeSceneRequestDTO = new ChangeSceneRequestDTO(SceneType.SPLASH);
    private Context context;
    private ScenePreemptLoad backgroundLoader;
    private BaseScene gameLoadSpinner;

    public MainScene(Context context, String sceneId) {
        super(sceneId);

        this.context = context;
        gameLoadSpinner = new GameLoadSpinner(SceneType.SCENE_SPINNER.toString(), context);

        hardSwapper = new SceneHardChange(this, gameLoadSpinner, 1000);


        // This will pre-load the menu scene on a background thread
        backgroundLoader = new ScenePreemptLoad(this, gameLoadSpinner, 0,
                () -> new MenuScene(SceneType.MENU.toString(), changeSceneRequestDTO, context), SceneType.MENU.toString());

        registerScene(new SplashScene(SceneType.SPLASH.toString(), context, changeSceneRequestDTO));
    }


    @Override
    public void runLogic() {
        super.runLogic();

        if (changeSceneRequestDTO.isChangeRequested()) {
            switch (changeSceneRequestDTO.getCurrentScene()) {
                case MENU:
                    hardSwapper.requestSceneChange(() -> new GamePlayScene(context, SceneType.GAMEPLAY.toString()), List.of(SceneType.MENU.toString()));
                    changeSceneRequestDTO.setCurrentScene(SceneType.GAMEPLAY);
                    break;
                case SPLASH:
                    backgroundLoader.requestSceneChange(SceneType.MENU.toString(), List.of(SceneType.SPLASH.toString()));
                    changeSceneRequestDTO.setCurrentScene(SceneType.MENU);
                    break;
                case GAMEPLAY:
                    // Note: this doesn't do anything for now... (user cannot go back to menu after game play scenes starts)
                    hardSwapper.requestSceneChange(() -> new MenuScene(SceneType.MENU.toString(), changeSceneRequestDTO, context), List.of(SceneType.GAMEPLAY.toString()));
                    changeSceneRequestDTO.setCurrentScene(SceneType.MENU);
                    break;
            }

            changeSceneRequestDTO.resolveChangeRequest();
        }

        try {
            // Check and add initialized scenes to this parent scene manager
            hardSwapper.joinScenes();
            backgroundLoader.joinScenes();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}