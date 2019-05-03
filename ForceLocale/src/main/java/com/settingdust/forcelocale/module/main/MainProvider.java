package com.settingdust.forcelocale.module.main;

import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.api.Config;
import com.settingdust.dustcore.api.ConfigProvider;
import com.settingdust.forcelocale.ForceLocale;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

public class MainProvider extends ConfigProvider<MainEntity> {
    @Getter
    private Set<MainEntity> entities;

    public MainProvider() {
        super(new Config(
                Paths.get("config.conf"),
                ForceLocale.getInstance().getConfigDir(),
                TypeToken.of(MainEntity.class)
        ), new MainEntity());

        Sponge.getEventManager().registerListeners(ForceLocale.getInstance(), new MessageHandler(this));
    }

    @Override
    public void load() {
        final TypeToken<MainEntity> typeToken = TypeToken.of(MainEntity.class);
        final Path configDir = ForceLocale.getInstance().getConfigDir();
        this.config = new Config(Paths.get("config.conf"), configDir, typeToken);

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
        try {
            entities = Sets.newHashSet();
            Files.list(configDir)
                    .filter(path -> path.toString().endsWith(".conf"))
                    .forEach(path -> {
                        Config config = new Config(path, configDir, typeToken);
                        try {
                            MainEntity entity = config.getRoot().getValue(TypeToken.of(MainEntity.class));
                            entities.add(entity);
                            config.save(entity);
                        } catch (ObjectMappingException | IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
