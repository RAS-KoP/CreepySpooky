package io.github.ras_kop.creepyspooky.entity.blockEntity;


import io.github.ras_kop.creepyspooky.energy.KekkaiSystemComponent;
import io.github.ras_kop.creepyspooky.register.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class HokoraMultiblockBlockEntity extends BlockEntity{

    public static final String ENTITY_ID = "hokora_multiblock_blockentity";

    public boolean multiBlock_flag;

    public static final KekkaiSystemComponent kekkai_system = 
        new KekkaiSystemComponent(0, 0);

    public HokoraMultiblockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegister.HOKORA_MULTIBLOCK_BLOCKENTITY.get(), pos, blockState);
        multiBlock_flag = false;
    }

    public void checkMultiblock(){
        Direction facing = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

        BlockPos backPos = getBlockPos().relative(facing.getOpposite());

        multiBlock_flag = level.getBlockState(backPos).is(Blocks.OAK_SAPLING);
    }
}
