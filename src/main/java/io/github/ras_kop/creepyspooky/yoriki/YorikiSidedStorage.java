package io.github.ras_kop.creepyspooky.yoriki;

import net.neoforged.neoforge.energy.IEnergyStorage;

public final class YorikiSidedStorage implements IEnergyStorage {
    private final IEnergyStorage delegate;
    private final boolean canReceive;
    private final boolean canExtract;

    public YorikiSidedStorage(IEnergyStorage delegate, boolean canReceive, boolean canExtract) {
        this.delegate = delegate;
        this.canReceive = canReceive;
        this.canExtract = canExtract;
    }

    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
        return canReceive ? delegate.receiveEnergy(toReceive, simulate) : 0;
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
        return canExtract ? delegate.extractEnergy(toExtract, simulate) : 0;
    }

    @Override
    public int getEnergyStored() {
        return delegate.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return delegate.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return canExtract && delegate.canExtract();
    }

    @Override
    public boolean canReceive() {
        return canReceive && delegate.canReceive();
    }
}
