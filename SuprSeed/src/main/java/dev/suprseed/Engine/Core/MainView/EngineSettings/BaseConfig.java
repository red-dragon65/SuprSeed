package dev.suprseed.Engine.Core.MainView.EngineSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseConfig<T> {

    protected List<Configurable<T>> settings;

    public BaseConfig() {

        settings = new ArrayList<>();
    }


    public void applySettings(T someClass) {

        for (Configurable<T> set : settings) {

            if (set.active()) {

                set.applySettings(someClass);
            }
        }
    }


    public Optional<Configurable<T>> getSetting(String id) {

        for (Configurable<T> setting : settings) {

            if (setting.getId().equals(id)) {

                return Optional.of(setting);
            }
        }

        return Optional.empty();
    }
}
