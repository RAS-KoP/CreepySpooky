package io.github.ras_kop.creepyspooky.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.attribute.EnergyWispAttribute;
import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Mob;
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


public class EnergyWispEntity extends FlyingMob implements GeoEntity, IYoryokuHolder{
    
    public static final String ENTITY_ID = "energy_wisp";

    private final YoryokuEnergyComponent yoryoku_energy = 
        new YoryokuEnergyComponent(2000);

    @Override
    public YoryokuEnergyComponent getEnergyComponent(){
        return yoryoku_energy;
    }

    public EnergyWispEntity(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.noPhysics = true;
        this.now_target = 0;
        this.state = WorkState.Transport;
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
            .add(Attributes.MOVEMENT_SPEED, 0.08)
            .add(EnergyWispAttribute.YORYOKU_IMPORT_SPEED, 200)
            .add(EnergyWispAttribute.YORYOKU_EXPORT_SPEED, 200);
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("YoryokuEnergyAmount", this.yoryoku_energy.getYoryoku());
        tag.putInt("YoryokuEnergyCapacity", this.yoryoku_energy.getCapacity());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.yoryoku_energy.setCapacity(tag.getInt("YoryokuEnergyCapacity"));
        this.yoryoku_energy.setYoryoku(tag.getInt("YoryokuEnergyAmount"));
    }

    
    //エネルギー系の処理

    public enum WorkState{
        InOut,
        Transport
    }

    private WorkState state;

    public WorkState getState(){
        return state;
    }

    public void setState(WorkState stat){
        this.state = stat;
    }

    private List<BlockPos> targets = new ArrayList<>();
    private int now_target;
    

    public void addTargetBlock(BlockPos target){
        this.targets.add(target);
    }

    public void setHome(BlockPos home){
        this.targets.set(0, home);
    }

    public BlockPos getTargetBlockPos(){
        return targets.get(now_target);
    }

    public void nextTarget(){
        if(targets.size() <= 1){
            now_target = 0;
            return;
        }
        now_target++;
        if(now_target >= targets.size()){
            now_target -= targets.size()-1;
        }
    }
}
