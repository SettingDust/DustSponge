package com.settingdust.dustcore.locale;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.utils.PlaceholderUtils;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Locale;

public class LocaleSerializer implements TypeSerializer<Locale> {
    @Nullable
    @Override
    public Locale deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        String[] array = value.getString().split("_");
        return new Locale(array[0], array[1]);
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Locale obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        value.setValue(PlaceholderUtils.forLanguage("%language_name%", obj));
    }
}
