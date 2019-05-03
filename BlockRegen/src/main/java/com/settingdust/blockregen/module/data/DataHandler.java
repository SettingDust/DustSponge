package com.settingdust.blockregen.module.data;

import com.settingdust.blockregen.BlockRegen;
import com.settingdust.blockregen.module.main.MainEntity;
import com.settingdust.blockregen.module.main.MainProvider;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DataHandler {
    private DataProvider dataProvider;
    private MainProvider mainProvider;

    public DataHandler(DataProvider dataProvider, MainProvider mainProvider) {
        this.dataProvider = dataProvider;
        this.mainProvider = mainProvider;
    }

    @Listener
    public void onStarted(GameStartedServerEvent event) {
        DataEntity dataEntity = dataProvider.get();
        Map<Location<World>, Date> data = dataEntity.getData();
        for (Location<World> location : data.keySet()) {
            Map<BlockType, MainEntity.Block> blocksConfig = mainProvider.getBlockConfig(location.getExtent());
            MainEntity.Block blockConfig = blocksConfig.get(location.getBlockType());

            Date now = new Date();
            long delta = data.get(location).getTime() - now.getTime();
            if (delta < 0) delta = 0;
            Sponge.getScheduler().createSyncExecutor(BlockRegen.getInstance()).schedule(() -> {
                mainProvider.getRandomBlockType(blockConfig.getBlockWeights())
                        .ifPresent(location::setBlockType);
            }, delta, TimeUnit.MILLISECONDS);
        }
    }
}
