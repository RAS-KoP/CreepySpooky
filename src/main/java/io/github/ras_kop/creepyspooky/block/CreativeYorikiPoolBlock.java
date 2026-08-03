package io.github.ras_kop.creepyspooky.block;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.block.entity.CreativeYorikiPoolBlockEntity;
import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class CreativeYorikiPoolBlock extends BaseEntityBlock {
    public static final MapCodec<CreativeYorikiPoolBlock> CODEC = simpleCodec(CreativeYorikiPoolBlock::new);

    public CreativeYorikiPoolBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<CreativeYorikiPoolBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CreativeYorikiPoolBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(
            blockEntityType,
            YorikiBlockEntityRegister.CREATIVE_YORIKI_POOL.get(),
            CreativeYorikiPoolBlockEntity::serverTick
        );
    }
}
