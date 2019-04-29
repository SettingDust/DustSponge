package com.settingdust.dustcore.module.main;

import lombok.Getter;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.text.translation.locale.NamedLocales;

import java.util.Locale;

@ConfigSerializable
public class MainEntity {
    @Getter
    @Setting(comment = "The language of plugin")
    private Locale locale = NamedLocales.DEFAULT;
}
