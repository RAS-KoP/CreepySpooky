package io.github.ras_kop.creepyspooky.block;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.block.entity.YorikiReceiverBlockEntity;
import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class YorikiReceiverBlock extends YorikiDirectionalBlock {
    public static final MapCodec<YorikiReceiverBlock> CODEC = simpleCodec(YorikiReceiverBlock::new);

    public YorikiReceiverBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<YorikiReceiverBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new YorikiReceiverBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(
            blockEntityType,
            YorikiBlockEntityRegister.YORIKI_RECEIVER.get(),
            YorikiReceiverBlockEntity::serverTick
        );
    }
}
