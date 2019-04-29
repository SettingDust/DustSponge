package com.settingdust.blockregen.module.main;

import com.settingdust.blockregen.BlockRegen;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.world.World;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BlockRegenHandler {
    private final MainProvider mainProvider;

    public BlockRegenHandler(MainProvider mainProvider) {
        this.mainProvider = mainProvider;
    }

    @Listener
    public void onBreak(ChangeBlockEvent.Break event, @Getter("getSource") Object source) {
        if (source instanceof Entity && ((Entity) source).getType().equals(EntityTypes.PLAYER)) {
            Player player = (Player) source;
            Map<BlockType, MainEntity.Block> blocksConfig = getBlockConfig(player.getWorld());
            for (Transaction<BlockSnapshot> blockSnapshotTransaction : event.getTransactions()) {
                BlockSnapshot blockSnapshot = blockSnapshotTransaction.getOriginal();
                BlockType blockType = blockSnapshot.getExtendedState().getType();
                if (blocksConfig.containsKey(blockType)) {
                    MainEntity.Block blockConfig = blocksConfig.get(blockType);

                    // 替换方块
                    blockSnapshotTransaction.setCustom(
                            BlockSnapshot.builder()
                                    .from(blockSnapshot)
                                    .blockState(
                                            BlockState.builder()
                                                    .blockType(blockConfig.getReplaceWith())
                                                    .build()
                                    ).build()
                    );
                    Sponge.getScheduler().createSyncExecutor(BlockRegen.getInstance()).schedule(() -> {
                        blockSnapshot.getLocation().ifPresent(
                                location -> getRandomBlockType(blockConfig.getBlockWeights())
                                        .ifPresent(location::setBlockType)
                        );
                    }, blockConfig.getRegenTime(), TimeUnit.SECONDS);
                }
            }
        }
    }

    private Optional<BlockType> getRandomBlockType(Map<BlockType, Integer> blockWeights) {
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

    private Map<BlockType, MainEntity.Block> getBlockConfig(World world) {
        final MainEntity mainEntity = mainProvider.get();
        return mainEntity.getWorlds().getOrDefault(world.getName(), mainEntity.getDefaultBlocks());
    }
}
