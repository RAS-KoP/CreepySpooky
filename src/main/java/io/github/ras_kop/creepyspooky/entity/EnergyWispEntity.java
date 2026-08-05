package io.github.ras_kop.creepyspooky.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;


public class EnergyWispEntity extends PathfinderMob implements GeoEntity{
    
    public static final String ENTITY_ID = "energy_wisp";

    public EnergyWispEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    private static final RawAnimation ANIMATION =
        RawAnimation.begin().thenLoop("animation");

    @Override
    public void registerControllers(
        AnimatableManager.ControllerRegistrar controllers) {

        controllers.add(
            new AnimationController<>(
                this,
                "animation",
                0,
                this::FryingAnimationController
            )
        ); 
    }

    protected <E extends EnergyWispEntity> PlayState FryingAnimationController(final AnimationState<E> event) {
        return event.setAndContinue(ANIMATION);
    }


    private final AnimatableInstanceCache cache =
        GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 20)
            .add(Attributes.MOVEMENT_SPEED, 0.25);
    }
}
