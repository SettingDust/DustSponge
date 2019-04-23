package com.settingdust.dustcore.api;

import com.google.inject.Provider;

public interface IProvider<T> extends Provider<T> {
    void save(Object value);

    void load();
}
