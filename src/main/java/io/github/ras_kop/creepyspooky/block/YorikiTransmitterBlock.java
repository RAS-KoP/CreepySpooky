package io.github.ras_kop.creepyspooky.block;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.block.entity.YorikiTransmitterBlockEntity;
import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class YorikiTransmitterBlock extends YorikiDirectionalBlock {
    public static final MapCodec<YorikiTransmitterBlock> CODEC = simpleCodec(YorikiTransmitterBlock::new);

    public YorikiTransmitterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<YorikiTransmitterBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new YorikiTransmitterBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(
            blockEntityType,
            YorikiBlockEntityRegister.YORIKI_TRANSMITTER.get(),
            YorikiTransmitterBlockEntity::serverTick
        );
    }
}
