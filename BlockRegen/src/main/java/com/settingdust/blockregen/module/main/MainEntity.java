package com.settingdust.blockregen.module.main;

import com.google.common.collect.Maps;
import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;

import java.util.Map;

@Data
@ConfigSerializable
public class MainEntity {
    @Setting(comment = "Default blocks", value = "default")
    private Map<BlockType, Block> defaultBlocks = Maps.newHashMap();

    @Setting(comment = "Different worlds' config")
    private Map<String, Map<BlockType, Block>> worlds = Maps.newHashMap();

    public MainEntity() {
        Block defaultOre = new Block();
        Map<BlockType, Integer> blockWeights = defaultOre.getBlockWeights();

        blockWeights.put(BlockTypes.AIR, 60);
        blockWeights.put(BlockTypes.COAL_ORE, 30);
        blockWeights.put(BlockTypes.IRON_ORE, 10);
        blockWeights.put(BlockTypes.GOLD_ORE, 5);
        blockWeights.put(BlockTypes.REDSTONE_ORE, 5);
        blockWeights.put(BlockTypes.LAPIS_ORE, 2);
        blockWeights.put(BlockTypes.DIAMOND_ORE, 2);
        blockWeights.put(BlockTypes.EMERALD_ORE, 1);

        defaultOre.setBlockWeights(blockWeights);

        defaultBlocks.put(BlockTypes.EMERALD_ORE, defaultOre);

        worlds.put("world", Maps.newHashMap(defaultBlocks));

        defaultBlocks.put(BlockTypes.DIAMOND_ORE, defaultOre);
        defaultBlocks.put(BlockTypes.LAPIS_ORE, defaultOre);
        defaultBlocks.put(BlockTypes.REDSTONE_ORE, defaultOre);
        defaultBlocks.put(BlockTypes.GOLD_ORE, defaultOre);
        defaultBlocks.put(BlockTypes.IRON_ORE, defaultOre);
    }

    @Data
    @ConfigSerializable
    public static final class Block {
        @Setting(comment = "Block regen time (seconds)")
        private int regenTime = 3000;

        @Setting(comment = "Replace when the block being broken")
        private BlockType replaceWith = BlockTypes.AIR;

        @Setting(comment = "Weight of types")
        private Map<BlockType, Integer> blockWeights = Maps.newHashMap();

        Block() {
            blockWeights.put(BlockTypes.STONE, 40);
            blockWeights.put(BlockTypes.AIR, 20);
            blockWeights.put(BlockTypes.COAL_ORE, 20);
            blockWeights.put(BlockTypes.IRON_ORE, 12);
            blockWeights.put(BlockTypes.GOLD_ORE, 6);
            blockWeights.put(BlockTypes.DIAMOND_ORE, 2);
        }
    }
}
