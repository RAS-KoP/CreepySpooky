package io.github.ras_kop.creepyspooky.model.entity;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class EnergyWispModel 
    extends DefaultedEntityGeoModel<EnergyWispEntity>{
    
    public EnergyWispModel(){
        super(ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                EnergyWispEntity.ENTITY_ID));
    }
}
