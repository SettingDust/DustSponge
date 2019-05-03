package com.settingdust.blockregen.module.main;

import com.google.common.reflect.TypeToken;
import com.settingdust.blockregen.BlockRegen;
import com.settingdust.blockregen.module.ProviderManager;
import com.settingdust.dustcore.api.Config;
import com.settingdust.dustcore.api.ConfigProvider;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.world.World;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MainProvider extends ConfigProvider<MainEntity> {
    private BlockRegen plugin = BlockRegen.getInstance();
    private final static Path path = Paths.get("config.conf");

    public MainProvider(ProviderManager providerManager) {
        super(new Config(
                path,
                BlockRegen.getInstance().getConfigDir(),
                TypeToken.of(MainEntity.class)
        ), new MainEntity());

        Sponge.getEventManager().registerListeners(plugin, new BlockRegenHandler(this, providerManager));
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

    public Optional<BlockType> getRandomBlockType(Map<BlockType, Integer> blockWeights) {
        Optional<BlockType> optionalRandomBlockType = Optional.empty();
        int total = 0;
        for (Integer value : blockWeights.values()) {
            total += value;
        }
        double random = Math.random() * total;
        for (BlockType blockType : blockWeights.keySet()) {
            random -= blockWeights.get(blockType);
            if (random <= 0) {
                optionalRandomBlockType = Optional.of(blockType);
                break;
            }
        }
        return optionalRandomBlockType;
    }

    public Map<BlockType, MainEntity.Block> getBlockConfig(World world) {
        final MainEntity mainEntity = this.get();
        return mainEntity.getWorlds().getOrDefault(world.getName(), mainEntity.getDefaultBlocks());
    }
}
