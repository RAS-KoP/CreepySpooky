package io.github.ras_kop.creepyspooky.entity.blockEntity;

import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;
import io.github.ras_kop.creepyspooky.register.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CreativeYoryokuResourceBlockBlockEntity extends BlockEntity implements IYoryokuHolder{

    public static final String ENTITY_ID = "creative_yoryoku_resource_block_blockentity";

    public CreativeYoryokuResourceBlockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegister.CREATIVE_YORYOKU_RESOURCE_BLOCK_BLOCKENTITY.get(), pos, blockState);
    }

    private final YoryokuEnergyComponent yoryoku_energy = new YoryokuEnergyComponent(Integer.MAX_VALUE);

    @Override
    public YoryokuEnergyComponent getYoryokuComponent() {
        return yoryoku_energy;
    }

    public static void tick(
        Level level,
        BlockPos pos,
        BlockState state,
        CreativeYoryokuResourceBlockBlockEntity blockEntity
    ){
        if (level.isClientSide) {
            return;
        }

        blockEntity.setYoryoku(Integer.MAX_VALUE/2);
    }
}
