package com.settingdust.dustcore.api;

import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;

public abstract class ConfigProvider<T> implements IProvider<T> {
    protected IConfig config;
    protected T entity;

    protected ConfigProvider(IConfig config, T entity) {
        this.config = config;
        this.entity = entity;
        this.load();
    }

    @Override
    public T get() {
        return entity;
    }

    @Override
    public void save(Object value) {
        try {
            config.save(value);
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }
    }
}
