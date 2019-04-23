package com.settingdust.dustmultirespawn.module.main.entity;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@Data
@ConfigSerializable
public class SignEntity {
    @Setting(comment = "Whether sync spawn when 'signBlock' be placed/broken")
    private boolean enable = false;
    @Setting(comment = "The Block to sync when place/break")
    private String type = "minecraft:torch";
}