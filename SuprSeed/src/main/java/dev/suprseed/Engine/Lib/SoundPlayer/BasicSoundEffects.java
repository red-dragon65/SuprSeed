package dev.suprseed.Engine.Lib.SoundPlayer;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.Map;
import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;

public class BasicSoundEffects<T> implements SoundMixer<T> {

    // Hold volume for left and right
    private final float volume = 1.0f;
    // Hold number of max sound streams
    private final int maxSounds = 10;
    // Hold sound player
    private final SoundPool soundPool;
    private boolean isSoundEnabled = true;
    // Hold user sounds
    private Map<T, Integer> soundMap;

    private int loopVal = 0;

    // Constructor
    public BasicSoundEffects() {

        // Create the soundPool
        soundPool = new SoundPool.Builder()
                .setMaxStreams(maxSounds)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .build();

    }


    @Override
    public void loadSounds(Map<T, Integer> sounds, Context context) {

        // Hold the given sounds
        soundMap = sounds;

        // Overwrite sounds with loaded sound pool id
        soundMap.replaceAll((k, v) -> soundPool.load(context, v, 1));
    }


    @Override
    public void playSound(T soundId) {

        playSound(soundId, false);
    }


    @Override
    public void playSound(T soundId, boolean loop) {

        if (loop) {
            loopVal = -1;
        } else {
            loopVal = 0;
        }


        // See if sound is enabled
        if (isSoundEnabled) {

            // See if sound requested exists in loaded sounds
            if (soundMap.containsKey(soundId)) {

                Optional<Integer> item = Optional.ofNullable(soundMap.get(soundId));

                item.ifPresent(integer -> soundPool.play(integer, volume, volume, 1, loopVal, 1f));

            } else {

                CentralLogger.getInstance().logMessage(ErrorType.WARNING, "The requested sound '" + soundId + "' does not exist in the loaded sound list!");
            }

        }
    }

    @Override
    public void clearSounds() {

        soundMap.clear();
    }

    @Override
    public void enableSound() {
        isSoundEnabled = true;
    }

    @Override
    public void disableSound() {
        isSoundEnabled = false;
    }
}
