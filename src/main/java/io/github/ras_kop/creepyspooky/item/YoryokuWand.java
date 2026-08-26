package io.github.ras_kop.creepyspooky.item;

import java.util.function.Consumer;

import io.github.ras_kop.creepyspooky.renderer.item.YoryokuWandRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtil;


public class YoryokuWand extends Item implements GeoItem {


    private final AnimatableInstanceCache cache =
            GeckoLibUtil.createInstanceCache(this);

    public YoryokuWand(Properties properties) {
        super(properties);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(
            new AnimationController<>(
                this,
                "Walking",
                5,
                state -> {
                    // 現在のItemDisplayContextを取得
                    ItemDisplayContext perspective =
                            state.getData(DataTickets.ITEM_RENDER_PERSPECTIVE);

                    // 手に持って表示されているときだけアニメーション
                    boolean isHeld =
                            perspective == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND ||
                            perspective == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ||
                            perspective == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND ||
                            perspective == ItemDisplayContext.THIRD_PERSON_LEFT_HAND;

                    if (!isHeld) {
                        return PlayState.STOP;
                    }

                    LocalPlayer player = Minecraft.getInstance().player;

                    if (player == null) {
                        return PlayState.STOP;
                    }

                    boolean isMoving =
                            player.getDeltaMovement()
                                .horizontalDistanceSqr() > 0.0001;

                    return state.setAndContinue(
                        isMoving
                            ? DefaultAnimations.WALK
                            : DefaultAnimations.IDLE
                    );
                }
            )
        );
    }

    @Override
    public boolean isPerspectiveAware() {
        return true;
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

    @Override
    public double getTick(Object itemStack) {
        return RenderUtil.getCurrentSystemTick();
    }

    
}
