package dev.suprseed.demo.Engine.Lib.SoundPlayer;

import android.content.Context;

import java.util.Map;

public interface SoundMixer<T> {

    void loadSounds(Map<T, Integer> sounds, Context context);

    void playSound(T soundID);

    void playSound(T soundID, boolean loop);

    void clearSounds();

    void enableSound();

    void disableSound();
}
