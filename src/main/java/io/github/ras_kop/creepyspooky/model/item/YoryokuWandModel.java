package io.github.ras_kop.creepyspooky.model.item;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.item.YoryokuWand;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class YoryokuWandModel extends GeoModel<YoryokuWand> {

    @Override
    public ResourceLocation getModelResource(YoryokuWand animatable) {
        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "geo/item/yoryoku_wand.geo.json"
        );
    }

    @Override
    public ResourceLocation getTextureResource(YoryokuWand animatable) {
        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "textures/item/yoryoku_wand.png"
        );
    }

    @Override
    public ResourceLocation getAnimationResource(YoryokuWand animatable) {
        return ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                "animations/item/yoryoku_wand.animation.json"
        );
    }
    
}
