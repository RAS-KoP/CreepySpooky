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

public final class YorikiTransmitterBlockEntity extends YorikiEnergyBlockEntity {
    private static final String RECEIVER_POS = "YorikiReceiverPos";
    private static final String RECEIVER_DIMENSION = "YorikiReceiverDimension";
    private final IEnergyStorage inputStorage;
    @Nullable
    private BlockPos linkedReceiver;
    @Nullable
    private String linkedReceiverDimension;

    public YorikiTransmitterBlockEntity(BlockPos pos, BlockState state) {
        super(
            YorikiBlockEntityRegister.YORIKI_TRANSMITTER.get(),
            pos,
            state,
            YorikiConstants.NETWORK_CAPACITY,
            YorikiConstants.NETWORK_TRANSFER_RATE,
            YorikiConstants.NETWORK_TRANSFER_RATE
        );
        this.inputStorage = new YorikiSidedStorage(energyStorage, true, false);
    }

    @Override
    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        Direction facing = getBlockState().getValue(YorikiDirectionalBlock.FACING);
        if (side == null || side == facing.getOpposite()) {
            return inputStorage;
        }
        return null;
    }

    public void setLinkedReceiver(Level level, BlockPos pos) {
        this.linkedReceiver = pos.immutable();
        this.linkedReceiverDimension = level.dimension().location().toString();
        setChanged();
    }

    @Nullable
    public BlockPos getLinkedReceiver() {
        return linkedReceiver;
    }

    @Nullable
    public String getLinkedReceiverDimension() {
        return linkedReceiverDimension;
    }

    public void clearLinkedReceiver() {
        this.linkedReceiver = null;
        this.linkedReceiverDimension = null;
        setChanged();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, YorikiTransmitterBlockEntity blockEntity) {
        if (blockEntity.linkedReceiver == null || blockEntity.linkedReceiverDimension == null
            || !blockEntity.linkedReceiverDimension.equals(level.dimension().location().toString())) {
            return;
        }

        BlockEntity target = level.getBlockEntity(blockEntity.linkedReceiver);
        if (target instanceof YorikiReceiverBlockEntity receiver) {
            int offered = blockEntity.energyStorage.extractEnergy(YorikiConstants.NETWORK_TRANSFER_RATE, true);
            int received = receiver.receiveWirelessEnergy(pos, offered, false);
            if (received > 0) {
                blockEntity.energyStorage.extractEnergy(received, false);
            }
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains(RECEIVER_POS, Tag.TAG_LONG) && tag.contains(RECEIVER_DIMENSION, Tag.TAG_STRING)) {
            linkedReceiver = BlockPos.of(tag.getLong(RECEIVER_POS));
            linkedReceiverDimension = tag.getString(RECEIVER_DIMENSION);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (linkedReceiver != null && linkedReceiverDimension != null) {
            tag.putLong(RECEIVER_POS, linkedReceiver.asLong());
            tag.putString(RECEIVER_DIMENSION, linkedReceiverDimension);
        }
    }
}
