package io.github.ras_kop.creepyspooky.yoriki.block.entity;

import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import io.github.ras_kop.creepyspooky.yoriki.YorikiConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class YorikiReceiverBlockEntity extends YorikiEnergyBlockEntity {
    public YorikiReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(
            YorikiBlockEntityRegister.YORIKI_RECEIVER.get(),
            pos,
            state,
            YorikiConstants.NETWORK_CAPACITY,
            YorikiConstants.NETWORK_TRANSFER_RATE,
            YorikiConstants.NETWORK_TRANSFER_RATE
        );
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, YorikiReceiverBlockEntity blockEntity) {
        blockEntity.pushEnergy(level);
    }
}
