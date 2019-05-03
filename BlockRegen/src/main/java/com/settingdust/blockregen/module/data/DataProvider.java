package com.settingdust.blockregen.module.data;

import com.google.common.reflect.TypeToken;
import com.settingdust.blockregen.BlockRegen;
import com.settingdust.blockregen.module.ProviderManager;
import com.settingdust.dustcore.api.ConfigProvider;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.util.Objects;

public class DataProvider extends ConfigProvider<DataEntity> {
    private BlockRegen plugin = BlockRegen.getInstance();

    public DataProvider(ProviderManager providerManager) {
        super(new DataConfig(), new DataEntity());

        Sponge.getEventManager().registerListeners(plugin, new DataHandler(this, providerManager.getMainProvider()));
    }

    @Override
    public void load() {
        this.config = new DataConfig();
        try {
            if (Objects.isNull(config.getRoot().getValue())) {
                this.entity = new DataEntity();
            } else {
                this.entity = config.getRoot().getValue(TypeToken.of(DataEntity.class));
            }
            this.config.save(this.entity);
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }

    }
}
