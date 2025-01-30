package dev.suprseed.Engine.Lib.SoundPlayer;

import android.content.Context;

import java.util.Map;

public interface SoundMixer<T> {

    void loadSounds(Map<T, Integer> sounds, Context context);

    void playSound(T soundID);

    void playSound(T soundID, boolean loop);

    void playSound(T soundID, boolean loop, float volume);

    void pause(T soundID);

    void stop(T soundID);

    void resume(T soundID);

    void pauseAll();

    void stopAll();

    void resumeAll();

    void clearSounds();

    void enableSound();

    void disableSound();
}
