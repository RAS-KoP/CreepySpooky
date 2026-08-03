package io.github.ras_kop.creepyspooky.block.entity;

import javax.annotation.Nullable;

import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import io.github.ras_kop.creepyspooky.yoriki.YorikiConstants;
import io.github.ras_kop.creepyspooky.yoriki.YorikiSidedStorage;
import io.github.ras_kop.creepyspooky.block.YorikiDirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;

public final class YorikiReceiverBlockEntity extends YorikiEnergyBlockEntity {
    private static final String TRANSMITTER_POS = "YorikiTransmitterPos";
    private static final String TRANSMITTER_DIMENSION = "YorikiTransmitterDimension";
    private final IEnergyStorage outputStorage;
    private final IEnergyStorage wirelessInputStorage;
    @Nullable
    private BlockPos linkedTransmitter;
    @Nullable
    private String linkedTransmitterDimension;

    public YorikiReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(
            YorikiBlockEntityRegister.YORIKI_RECEIVER.get(),
            pos,
            state,
            YorikiConstants.NETWORK_CAPACITY,
            YorikiConstants.NETWORK_TRANSFER_RATE,
            YorikiConstants.NETWORK_TRANSFER_RATE
        );
        this.outputStorage = new YorikiSidedStorage(energyStorage, false, true);
        this.wirelessInputStorage = new YorikiSidedStorage(energyStorage, true, false);
    }

    @Override
    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        if (side == null || side == getBlockState().getValue(YorikiDirectionalBlock.FACING)) {
            return outputStorage;
        }
        return null;
    }

    public int receiveWirelessEnergy(BlockPos transmitterPos, int toReceive, boolean simulate) {
        if (linkedTransmitter == null || !linkedTransmitter.equals(transmitterPos)) {
            return 0;
        }
        return wirelessInputStorage.receiveEnergy(toReceive, simulate);
    }

    public void setLinkedTransmitter(Level level, BlockPos pos) {
        this.linkedTransmitter = pos.immutable();
        this.linkedTransmitterDimension = level.dimension().location().toString();
        setChanged();
    }

    @Nullable
    public BlockPos getLinkedTransmitter() {
        return linkedTransmitter;
    }

    @Nullable
    public String getLinkedTransmitterDimension() {
        return linkedTransmitterDimension;
    }

    public void clearLinkedTransmitter() {
        this.linkedTransmitter = null;
        this.linkedTransmitterDimension = null;
        setChanged();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, YorikiReceiverBlockEntity blockEntity) {
        blockEntity.pushEnergy(level);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains(TRANSMITTER_POS, Tag.TAG_LONG) && tag.contains(TRANSMITTER_DIMENSION, Tag.TAG_STRING)) {
            linkedTransmitter = BlockPos.of(tag.getLong(TRANSMITTER_POS));
            linkedTransmitterDimension = tag.getString(TRANSMITTER_DIMENSION);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (linkedTransmitter != null && linkedTransmitterDimension != null) {
            tag.putLong(TRANSMITTER_POS, linkedTransmitter.asLong());
            tag.putString(TRANSMITTER_DIMENSION, linkedTransmitterDimension);
        }
    }
}
