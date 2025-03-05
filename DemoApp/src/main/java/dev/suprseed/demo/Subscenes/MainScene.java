package dev.suprseed.demo.Subscenes;

import android.content.Context;

import java.util.concurrent.ExecutionException;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.Scenes.SceneStrategy.SceneHardChange;
import dev.suprseed.demo.Subscenes.GamePlay.GamePlayScene;
import dev.suprseed.demo.Subscenes.LoadingScreen.GameLoadSpinner;
import dev.suprseed.demo.Subscenes.Menu.MenuScene;

public class MainScene extends SceneManager {

    private SceneHardChange swapper;
    private ChangeSceneRequestDTO changeSceneRequestDTO = new ChangeSceneRequestDTO();
    private Context context;

    public MainScene(Context context, String sceneId) {
        super(sceneId);

        this.context = context;

        swapper = new SceneHardChange(this, new GameLoadSpinner("sceneSpinner", context));

        registerScene(new MenuScene("menu", changeSceneRequestDTO, context));

        // This gets created when the scene gets changed
        //new GamePlayScene(this, "gameplay");
    }

    @Override
    public void runLogic() {
        super.runLogic();

        // See if scenes should be changed
        if (changeSceneRequestDTO.isChangeRequested) {

            // Swap scenes
            if (changeSceneRequestDTO.currentScene == ChangeSceneRequestDTO.CurrentScene.MENU) {

                swapper.requestSceneChange(() -> new GamePlayScene(context, "gameplay"), "menu");

                changeSceneRequestDTO.isChangeRequested = false;
                changeSceneRequestDTO.currentScene = ChangeSceneRequestDTO.CurrentScene.GAMEPLAY;

            } else {

                swapper.requestSceneChange(() -> new MenuScene("menu", changeSceneRequestDTO, context), "gameplay");

                changeSceneRequestDTO.isChangeRequested = false;
                changeSceneRequestDTO.currentScene = ChangeSceneRequestDTO.CurrentScene.MENU;
            }
        }

        try {
            // This should never need to run
            swapper.joinScenes();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}