package com.settingdust.dustdeathpenalty.module.main.entity;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
@Data
public class EconomyEntity {
    @Setting(comment = "Whether drop money")
    private boolean enable = true;
    @Setting(comment = "Drop rate(<=1) or number(>1)")
    private double rate = 0.1D;
}
