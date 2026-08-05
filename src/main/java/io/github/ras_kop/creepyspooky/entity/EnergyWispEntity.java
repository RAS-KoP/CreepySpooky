package io.github.ras_kop.creepyspooky.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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

    private final YoryokuEnergyComponent yoryoku_energy = 
        new YoryokuEnergyComponent(2000);

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

    @SuppressWarnings("null")
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 20)
            .add(Attributes.MOVEMENT_SPEED, 0.25);
    }


    @SuppressWarnings("deprecation")
    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(
        @Nonnull ServerLevelAccessor level,
        @Nonnull DifficultyInstance difficulty,
        @Nonnull MobSpawnType spawnType, 
        @Nullable SpawnGroupData spawnGroupData) {



        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }


    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("YoryokuEnergyAmount", this.yoryoku_energy.getEnergy());
        tag.putInt("YoryokuEnergyCapacity", this.yoryoku_energy.getCapacity());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.yoryoku_energy.setCapacity(tag.getInt("YoryokuEnergyCapacity"));
        this.yoryoku_energy.setEnergy(tag.getInt("YoryokuEnergyAmount"));
    }
}
