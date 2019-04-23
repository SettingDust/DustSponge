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
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

@ConfigSerializable
public class Config implements IConfig {
    @Getter
    protected ConfigurationLoader<CommentedConfigurationNode> configLoader;
    @Getter
    private final Path configPath;

    private ConfigurationOptions options;

    private TypeToken typeToken = null;

    private ConfigurationNode rootNode;

    public Config(Path configPath, Path configDir) {
        this.configPath = configDir.resolve(configPath);
        this.load(this.configPath);
    }

    public Config(Path configPath, Path configDir, TypeToken typeToken) {
        this.configPath = configDir.resolve(configPath);
        this.load(this.configPath);

        this.typeToken = typeToken;

        // Register serializers
        TypeSerializerCollection serializers = TypeSerializers.getDefaultSerializers().newChild();
        serializers.registerType(TypeToken.of(Locale.class), new LocaleSerializer());
        options = ConfigurationOptions.defaults().setSerializers(serializers);

        try {
            rootNode = this.configLoader.load(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Object value) throws ObjectMappingException, IOException {
        if (configPath != null) {
            if (typeToken != null) {
                configLoader.save(rootNode.setValue(typeToken, value));
            } else {
                configLoader.save(rootNode.setValue(value));
            }
        }
    }

    public void load(Path configPath) {
        this.configLoader = HoconConfigurationLoader.builder().setPath(configPath).build();
        try {
            this.rootNode=this.configLoader.load();
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
