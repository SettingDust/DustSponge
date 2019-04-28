package com.settingdust.dustdeathpenalty.module.main;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.api.Config;
import com.settingdust.dustcore.api.ConfigProvider;
import com.settingdust.dustdeathpenalty.DustDeathPenalty;
import com.settingdust.dustdeathpenalty.module.main.entity.MainEntity;
import com.settingdust.dustdeathpenalty.module.main.handler.DropHandler;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class MainProvider extends ConfigProvider<MainEntity> {
    private DustDeathPenalty plugin = DustDeathPenalty.getInstance();

    public MainProvider() {
        super(new Config(
                Paths.get("config.conf"),
                DustDeathPenalty.getInstance().getConfigDir(),
                TypeToken.of(MainEntity.class)
        ), new MainEntity());
        this.load();
        Sponge.getEventManager().registerListeners(plugin, new DropHandler(this));
    }

    @Override
    public void load() {
        this.config = new Config(
                Paths.get("config.conf"),
                DustDeathPenalty.getInstance().getConfigDir(),
                TypeToken.of(MainEntity.class)
        );
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

    @Override
    public MainEntity get() {
        return entity;
    }
}
