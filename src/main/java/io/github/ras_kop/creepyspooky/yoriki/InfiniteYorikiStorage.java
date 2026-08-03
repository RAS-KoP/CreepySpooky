package io.github.ras_kop.creepyspooky.yoriki;

import net.neoforged.neoforge.energy.IEnergyStorage;

public final class InfiniteYorikiStorage implements IEnergyStorage {
    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
        return Math.min(YorikiConstants.NETWORK_TRANSFER_RATE, Math.max(0, toExtract));
    }

    @Override
    public int getEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
