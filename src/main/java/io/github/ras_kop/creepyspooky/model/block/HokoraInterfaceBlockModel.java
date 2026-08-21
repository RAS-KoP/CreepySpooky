package io.github.ras_kop.creepyspooky.model.block;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HokoraInterfaceBlockModel extends GeoModel<HokoraMultiblockBlockEntity>{
    
    @Override
    public ResourceLocation getModelResource(HokoraMultiblockBlockEntity animatable) {

        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "geo/block/hokora_interface_block.geo.json"
            );
    }

    @Override
    public ResourceLocation getTextureResource(HokoraMultiblockBlockEntity animatable) {

        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "textures/block/hokora_interface_block.png"
            );
    }

    @Override
    public ResourceLocation getAnimationResource(HokoraMultiblockBlockEntity animatable) {

        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "animations/block/hokora_interface_block.animation.json"
            );
    }
}
