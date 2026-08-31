package io.github.ras_kop.creepyspooky.model.item;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.item.HokoraInterfaceBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HokoraInterfaceBlockItemModel extends GeoModel<HokoraInterfaceBlockItem>{
    
    @Override
    public ResourceLocation getModelResource(HokoraInterfaceBlockItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "geo/block/hokora_interface_block.geo.json"
        );
    }

    @Override
    public ResourceLocation getTextureResource(HokoraInterfaceBlockItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "textures/block/hokora_interface_block.png"
        );
    }

    @Override
    public ResourceLocation getAnimationResource(HokoraInterfaceBlockItem animatable) {
        return null;
    }
}
