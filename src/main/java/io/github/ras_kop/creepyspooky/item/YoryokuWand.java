package io.github.ras_kop.creepyspooky.item;

import java.util.function.Consumer;

import io.github.ras_kop.creepyspooky.renderer.item.YoryokuWandRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class YoryokuWand extends Item implements GeoItem {


    private final AnimatableInstanceCache cache =
            GeckoLibUtil.createInstanceCache(this);

    public YoryokuWand(Properties properties) {
        super(properties);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(
            new GeoRenderProvider() {
                
                private final YoryokuWandRenderer renderer = new YoryokuWandRenderer();

                @Override
                public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                    return renderer;
                }
            }
        );
    }
    
}
