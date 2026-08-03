package io.github.ras_kop.creepyspooky.block.entity;

import javax.annotation.Nullable;

import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import io.github.ras_kop.creepyspooky.yoriki.InfiniteYorikiStorage;
import io.github.ras_kop.creepyspooky.yoriki.YorikiSidedStorage;
import io.github.ras_kop.creepyspooky.yoriki.YorikiTransfer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;

public final class CreativeYorikiPoolBlockEntity extends BlockEntity {
    private final IEnergyStorage energyStorage = new InfiniteYorikiStorage();
    private final IEnergyStorage outputStorage = new YorikiSidedStorage(energyStorage, false, true);

    public CreativeYorikiPoolBlockEntity(BlockPos pos, BlockState state) {
        super(YorikiBlockEntityRegister.CREATIVE_YORIKI_POOL.get(), pos, state);
    }

    @Nullable
    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        return outputStorage;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CreativeYorikiPoolBlockEntity blockEntity) {
        YorikiTransfer.pushEnergy(level, pos, blockEntity.energyStorage, Direction.values());
    }
}
