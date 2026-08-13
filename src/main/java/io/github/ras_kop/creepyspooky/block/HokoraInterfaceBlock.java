package io.github.ras_kop.creepyspooky.block;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HokoraInterfaceBlock extends BaseEntityBlock{

    public HokoraInterfaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new HokoraMultiblockBlockEntity(arg0, arg1)
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'codec'");
    }
}
