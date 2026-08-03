package io.github.ras_kop.creepyspooky.yoriki.block.entity;

import javax.annotation.Nullable;

import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import io.github.ras_kop.creepyspooky.yoriki.YorikiConstants;
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
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;

public final class YorikiCableBlockEntity extends BlockEntity {
    private final YorikiEnergyStorage energyStorage;
    private final IEnergyStorage sidedStorage;

    public YorikiCableBlockEntity(BlockPos pos, BlockState state) {
        super(YorikiBlockEntityRegister.YORIKI_CABLE.get(), pos, state);
        this.energyStorage = new YorikiEnergyStorage(
            YorikiConstants.NETWORK_CAPACITY,
            YorikiConstants.NETWORK_TRANSFER_RATE,
            YorikiConstants.NETWORK_TRANSFER_RATE,
            ignored -> setChanged()
        );
        this.sidedStorage = new YorikiSidedStorage(energyStorage, true, true);
    }

    @Nullable
    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        return sidedStorage;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, YorikiCableBlockEntity blockEntity) {
        YorikiTransfer.pushEnergy(level, pos, blockEntity.energyStorage, Direction.values());
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
