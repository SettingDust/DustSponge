package com.settingdust.dustcore.api;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.locale.LocaleSerializer;
import lombok.Getter;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializerCollection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;

@ConfigSerializable
public class Config implements IConfig {
    @Getter
    protected ConfigurationLoader<CommentedConfigurationNode> configLoader;
    @Getter
    private final Path configPath;

    @Getter
    private ConfigurationOptions options;

    private Optional<TypeToken> typeToken;

    private ConfigurationNode rootNode;

    public Config(Path configPath, Path configDir) {
        this.configPath = configDir.resolve(configPath);
        this.options = ConfigurationOptions.defaults();
        this.load(this.configPath);
        this.typeToken = Optional.empty();
    }

    public Config(Path configPath, Path configDir, TypeToken typeToken) {
        this.configPath = configDir.resolve(configPath);
        this.options = ConfigurationOptions.defaults();
        this.load(this.configPath);
        this.typeToken = Optional.of(typeToken);
    }

    public void save(Object value) throws ObjectMappingException, IOException {
        if (configPath != null) {
            File file = configPath.toFile().getParentFile();
            if (!file.exists()) {
                file.mkdirs();
            }
            if (typeToken.isPresent()) {
                configLoader.save(rootNode.setValue(typeToken.get(), value));
            } else {
                configLoader.save(rootNode.setValue(value));
            }
        }
    }

    public void load(Path configPath) {
        // Register serializers
        TypeSerializerCollection serializers = options.getSerializers();
        serializers.registerType(TypeToken.of(Locale.class), new LocaleSerializer());
        options.setSerializers(serializers);

        this.configLoader = HoconConfigurationLoader.builder().setPath(configPath).build();

        try {
            this.rootNode = this.configLoader.load(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        this.load(this.configPath);
    }

    public ConfigurationNode getRoot() {
        return rootNode;
    }
}
