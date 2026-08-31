package io.github.ras_kop.creepyspooky.entity.blockEntity;


import io.github.ras_kop.creepyspooky.api.IKekkaiSystemHolder;
import io.github.ras_kop.creepyspooky.energy.KekkaiSystemComponent;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity.BlockWorkRole;
import io.github.ras_kop.creepyspooky.register.BlockEntityRegister;
import io.github.ras_kop.creepyspooky.register.EntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HokoraMultiblockBlockEntity extends BlockEntity implements GeoBlockEntity, IKekkaiSystemHolder{

    public static final String ENTITY_ID = "hokora_multiblock_blockentity";

    public boolean multiBlock_flag;

    private final KekkaiSystemComponent kekkai_system = 
        new KekkaiSystemComponent(this, 0, 0);
        
    @Override
    public KekkaiSystemComponent getKekkaiSystemComponent() {
        return kekkai_system;
    }

    public HokoraMultiblockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegister.HOKORA_MULTIBLOCK_BLOCKENTITY.get(), pos, blockState);
        multiBlock_flag = false;
    }

    public void checkMultiblock(){

        Direction facing = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        BlockPos backPos = getBlockPos().relative(facing.getOpposite());
        multiBlock_flag = level.getBlockState(backPos).is(Blocks.OAK_SAPLING);

        if(multiBlock_flag){
            getKekkaiSystemComponent().setTier(1);
            getKekkaiSystemComponent().setCapacity(2000);
        }
    }


    public void spawnTransporter(BlockPos import_target, BlockPos export_target){
        if (level == null || level.isClientSide) {
            return;
        }

        EnergyWispEntity transporter = new EnergyWispEntity(
            EntityRegister.ENERGY_WISP.get(),
            level
        );
        transporter.setPersistenceRequired();

        BlockPos pos = getBlockPos();

        transporter.setPos(
            pos.getX(),
            pos.getY(),
            pos.getZ()
        );

        transporter.setTargetBlock(pos, BlockWorkRole.HOME);
        transporter.setTargetBlock(import_target, BlockWorkRole.IMPORT);
        transporter.setTargetBlock(export_target, BlockWorkRole.EXPORT);
        transporter.nextTarget();

        level.addFreshEntity(transporter);
    }



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    private final AnimatableInstanceCache cache =
        GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putBoolean("MultiBlockFlag", multiBlock_flag);

        int capacity = this.getKekkaiSystemComponent().getCapacity();
        int amount = this.getKekkaiSystemComponent().getYoryoku();
        tag.putInt("YoryokuEnergyCapacity", capacity);
        tag.putInt("YoryokuEnergyAmount", amount);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        multiBlock_flag = tag.getBoolean("MultiBlockFlag");

        int capacity = tag.getInt("YoryokuEnergyCapacity");
        int amount = tag.getInt("YoryokuEnergyAmount");
        this.getKekkaiSystemComponent().setCapacity(capacity);
        this.getKekkaiSystemComponent().setYoryoku(amount);
    }
}