package com.settingdust.dustcore.module.main;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.api.ConfigProvider;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class MainProvider extends ConfigProvider<MainEntity> {
    @Getter
    private Locale locale;

    public MainProvider() {
        super(
                new MainConfig(),
                new MainEntity()
        );
        this.load();
        locale = entity.getLocale();
    }

    @Override
    public void load() {
        this.config = new MainConfig();
        try {
            if (Objects.isNull(config.getRoot().getValue())) {
                this.entity = new MainEntity();
            } else {
                this.entity = config.getRoot().getValue(TypeToken.of(MainEntity.class));
            }
            this.config.save(this.entity);
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }
    }
}
