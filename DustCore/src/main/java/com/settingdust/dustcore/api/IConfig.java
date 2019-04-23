package com.settingdust.dustcore.api;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.nio.file.Path;

public interface IConfig {
    Path getConfigPath();

    ConfigurationLoader<CommentedConfigurationNode> getConfigLoader();

    void save(Object value) throws ObjectMappingException, IOException;

    void reload();

    ConfigurationNode getRoot();

    void load(Path configPath);
}
