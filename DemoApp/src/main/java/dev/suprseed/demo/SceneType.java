package dev.suprseed.demo;

import androidx.annotation.NonNull;

public enum SceneType {
    MENU("menu"),
    GAMEPLAY("gameplay"),
    SPLASH("splash"),
    SCENE_SPINNER("sceneSpinner");

    private String id;

    SceneType(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return id;
    }
}
