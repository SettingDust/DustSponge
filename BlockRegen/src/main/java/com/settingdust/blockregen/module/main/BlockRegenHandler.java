package com.settingdust.blockregen.module.main;

import com.settingdust.blockregen.BlockRegen;
import com.settingdust.blockregen.module.ProviderManager;
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
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BlockRegenHandler {
    private final MainProvider mainProvider;
    private ProviderManager providerManager;

    public BlockRegenHandler(MainProvider mainProvider, ProviderManager providerManager) {
        this.mainProvider = mainProvider;
        this.providerManager = providerManager;
    }

    @Listener
    public void onBreak(ChangeBlockEvent.Break event, @Getter("getSource") Object source) {
        if (source instanceof Entity && ((Entity) source).getType().equals(EntityTypes.PLAYER)) {
            Player player = (Player) source;
            Map<BlockType, MainEntity.Block> blocksConfig = mainProvider.getBlockConfig(player.getWorld());
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

                    Map<Location<World>, Date> data = providerManager.getDataProvider().get().getData();

                    Calendar calendar = Calendar.getInstance();
                    int regenTime = blockConfig.getRegenTime();
                    calendar.add(Calendar.SECOND, regenTime);
                    blockSnapshot.getLocation().ifPresent(location -> {
                        data.put(location, calendar.getTime());
                        Sponge.getScheduler().createSyncExecutor(BlockRegen.getInstance()).schedule(() -> {
                            mainProvider.getRandomBlockType(blockConfig.getBlockWeights())
                                    .ifPresent(location::setBlockType);
                        }, regenTime, TimeUnit.SECONDS);
                    });
                }
            }
        }
    }


}
