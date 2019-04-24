package com.settingdust.dustmultirespawn.module.spawns.handler;

import com.settingdust.dustmultirespawn.module.spawns.SpawnsProvider;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;

public class RespawnHandler {
    private SpawnsProvider spawnsProvider;

    public RespawnHandler(SpawnsProvider spawnsProvider) {
        this.spawnsProvider = spawnsProvider;
    }

    @Listener
    public void onRespawn(RespawnPlayerEvent event) {
        event.setToTransform(event.getToTransform().setLocation(spawnsProvider.getSpawnLocation(event.getFromTransform().getLocation())));
    }
}
