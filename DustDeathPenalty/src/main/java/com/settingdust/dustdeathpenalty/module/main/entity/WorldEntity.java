package com.settingdust.dustdeathpenalty.module.main.entity;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
@Data
public class WorldEntity {
    @Setting
    private ItemEntity item = new ItemEntity();
    @Setting
    private ExpEntity exp = new ExpEntity();
    @Setting
    private EconomyEntity economy = new EconomyEntity();
    @Setting(comment = "Send the drops to player")
    private boolean isSendMsg = false;

    public WorldEntity() {
    }

    public WorldEntity(ItemEntity item, ExpEntity exp, boolean isSendMsg) {
        this.item = item;
        this.exp = exp;
        this.isSendMsg = isSendMsg;
    }
}
