package io.github.ras_kop.creepyspooky.block.entity;

import javax.annotation.Nullable;

import io.github.ras_kop.creepyspooky.yoriki.YorikiEnergyStorage;
import io.github.ras_kop.creepyspooky.yoriki.YorikiSidedStorage;
import io.github.ras_kop.creepyspooky.yoriki.YorikiTransfer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;

public abstract class YorikiEnergyBlockEntity extends BlockEntity {
    protected final YorikiEnergyStorage energyStorage;
    private final IEnergyStorage inputStorage;
    private final IEnergyStorage outputStorage;

    protected YorikiEnergyBlockEntity(
        BlockEntityType<?> type,
        BlockPos pos,
        BlockState state,
        int capacity,
        int maxReceive,
        int maxExtract
    ) {
        super(type, pos, state);
        this.energyStorage = new YorikiEnergyStorage(capacity, maxReceive, maxExtract, ignored -> setChanged());
        this.inputStorage = new YorikiSidedStorage(energyStorage, true, false);
        this.outputStorage = new YorikiSidedStorage(energyStorage, false, true);
    }

    @Nullable
    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        if (side == null) {
            return energyStorage;
        }
        Direction facing = getBlockState().getValue(io.github.ras_kop.creepyspooky.block.YorikiDirectionalBlock.FACING);
        if (side == facing.getOpposite()) {
            return inputStorage;
        }
        if (side == facing) {
            return outputStorage;
        }
        return null;
    }

    protected final void pushEnergy(Level level) {
        Direction facing = getBlockState().getValue(io.github.ras_kop.creepyspooky.block.YorikiDirectionalBlock.FACING);
        YorikiTransfer.pushEnergy(level, getBlockPos(), energyStorage, facing);
    }

    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    public int getMaxEnergyStored() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("YorikiEnergy", Tag.TAG_INT)) {
            energyStorage.setEnergy(tag.getInt("YorikiEnergy"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("YorikiEnergy", energyStorage.getEnergyStored());
    }
}
