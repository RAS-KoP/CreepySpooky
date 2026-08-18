package io.github.ras_kop.creepyspooky.entity.blockEntity;


import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;
import io.github.ras_kop.creepyspooky.register.EntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HokoraMultiblockBlockEntity extends BlockEntity implements IYoryokuHolder{

    public static final String ENTITY_ID = "hokora_multiblock_blockentity";

    private static final YoryokuEnergyComponent yoryoku_energy =
        new YoryokuEnergyComponent(2000);

    public HokoraMultiblockBlockEntity(BlockPos pos, BlockState blockState) {
        super(EntityRegister.HOKORA_MULTIBLOCK_BLOCKENTITY.get(), pos, blockState);
    }

    @Override
    public YoryokuEnergyComponent getEnergyComponent() {
        return yoryoku_energy;
    }


}
