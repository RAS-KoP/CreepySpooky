package io.github.ras_kop.creepyspooky.item;

import java.util.function.Consumer;

import io.github.ras_kop.creepyspooky.renderer.item.HokoraInterfaceBlockItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HokoraInterfaceBlockItem extends BlockItem implements GeoItem{


    private final AnimatableInstanceCache cache =
            GeckoLibUtil.createInstanceCache(this);

    public HokoraInterfaceBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final HokoraInterfaceBlockItemRenderer renderer = new HokoraInterfaceBlockItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                return renderer;
            }
        }
    );
}
}
