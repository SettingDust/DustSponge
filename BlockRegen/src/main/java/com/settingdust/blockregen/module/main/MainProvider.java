package com.settingdust.blockregen.module.main;

import com.google.common.reflect.TypeToken;
import com.settingdust.blockregen.BlockRegen;
import com.settingdust.dustcore.api.Config;
import com.settingdust.dustcore.api.ConfigProvider;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class MainProvider extends ConfigProvider<MainEntity> {
    private BlockRegen plugin = BlockRegen.getInstance();
    private final static Path path = Paths.get("config.conf");

    public MainProvider() {
        super(new Config(
                path,
                BlockRegen.getInstance().getConfigDir(),
                TypeToken.of(MainEntity.class)
        ), new MainEntity());

        Sponge.getEventManager().registerListeners(plugin, new BlockRegenHandler(this));
    }

    @Override
    public void load() {
        this.config = new Config(
                path,
                BlockRegen.getInstance().getConfigDir(),
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
}
