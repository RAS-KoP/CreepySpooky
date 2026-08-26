package io.github.ras_kop.creepyspooky.entity;

import java.util.HashMap;
import java.util.Map;


import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.attribute.EnergyWispAttribute;
import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;
import io.github.ras_kop.creepyspooky.goal.EnergyWispInOutGoal;
import io.github.ras_kop.creepyspooky.goal.EnergyWispTransportGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.player.Player;
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
    public YoryokuEnergyComponent getYoryokuComponent(){
        return yoryoku_energy;
    }

    public EnergyWispEntity(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.noPhysics = true;
        this.now_target = BlockWorkRole.HOME;
        this.state = WorkState.Transport;
        this.idle_time = 0;
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
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("YoryokuEnergyAmount", this.yoryoku_energy.getYoryoku());
        tag.putInt("YoryokuEnergyCapacity", this.yoryoku_energy.getCapacity());
        tag.putString("EnergyWispTargetState", now_target.name());
        tag.putString("EnergyWispWorkState", state.name());

        ListTag list = new ListTag();
        for (Map.Entry<BlockWorkRole, BlockPos> entry : targets.entrySet()) { 
            CompoundTag Tags = new CompoundTag();
            BlockWorkRole role = entry.getKey();
            BlockPos pos = entry.getValue();

            Tags.putString("Role", role.name());
            Tags.putInt("X", pos.getX());
            Tags.putInt("Y", pos.getY());
            Tags.putInt("Z", pos.getZ());
            list.add(Tags);
        }
        tag.put("EnergyWispTargetPositions", list);

        System.out.println(
            "EnergyWisp SAVE: " + this.getUUID()
        );
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.yoryoku_energy.setCapacity(tag.getInt("YoryokuEnergyCapacity"));
        this.yoryoku_energy.setYoryoku(tag.getInt("YoryokuEnergyAmount"));
        now_target = BlockWorkRole.valueOf(tag.getString("EnergyWispTargetState"));
        state = WorkState.valueOf(tag.getString("EnergyWispWorkState"));

        targets.clear();
        ListTag list = tag.getList("EnergyWispTargetPositions", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag Tags = list.getCompound(i);

            BlockWorkRole role = BlockWorkRole.valueOf(Tags.getString("Role"));
            int x = Tags.getInt("X");
            int y = Tags.getInt("Y");
            int z = Tags.getInt("Z");
            targets.put(role, new BlockPos(x, y, z));
        }

        System.out.println(
            "EnergyWisp LOAD: " + this.getUUID()
        );
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(
            1,
            new EnergyWispTransportGoal(this)
        );
        this.goalSelector.addGoal(
            2,
            new EnergyWispInOutGoal(this)
        );
        this.goalSelector.addGoal(
            3,
            new RandomLookAroundGoal(this)
        );
    };


    @Override
    public void tick() {
        super.tick();

        if (state == WorkState.Idle) {
        idle_time++;

        if (idle_time >= 60) {
            this.kill();
        }
        } else {
            idle_time = 0;
        }
    }


    @Override
    public InteractionResult mobInteract(
            Player player,
            InteractionHand hand
    ) {
        Level level = player.level();
        if (!level.isClientSide) {
            player.sendSystemMessage(
                Component.literal("Energy: " + getYoryoku()+"/"+ getCapacity())
            );
        }
        return InteractionResult.SUCCESS;
    }

    
    //エネルギー系の処理

    public static enum WorkState{
        InOut,
        Transport,
        Idle
    }

    public static enum BlockWorkRole{
        HOME,
        IMPORT,
        EXPORT
    }

    private WorkState state;
    private int idle_time;

    public WorkState getState(){
        return state;
    }

    public void setState(WorkState stat){
        this.state = stat;
    }

    private Map<BlockWorkRole, BlockPos> targets = new HashMap<>();
    private BlockWorkRole now_target;
    

    public void setTargetBlock(BlockPos target, BlockWorkRole role){
        this.targets.put(role, target);
    }


    public Map.Entry<BlockWorkRole, BlockPos> getTargetBlockPos(){
        return Map.entry(now_target, targets.get(now_target));
    }

    public void nextTarget(){
        if(now_target == BlockWorkRole.IMPORT){
            now_target = BlockWorkRole.EXPORT;
        }else{
            now_target = BlockWorkRole.IMPORT;
        }
    }

    public void targetSetHome(){
        now_target = BlockWorkRole.HOME;
        state = WorkState.Transport;
    }
}
