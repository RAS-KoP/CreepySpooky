package io.github.ras_kop.creepyspooky.yoriki;

import java.util.function.IntConsumer;

import net.minecraft.util.Mth;
import net.neoforged.neoforge.energy.EnergyStorage;

public class YorikiEnergyStorage extends EnergyStorage {
    private final IntConsumer changeListener;

    public YorikiEnergyStorage(int capacity, int maxReceive, int maxExtract, IntConsumer changeListener) {
        super(capacity, maxReceive, maxExtract);
        this.changeListener = changeListener;
    }

    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
        int received = super.receiveEnergy(toReceive, simulate);
        if (!simulate && received > 0) {
            changeListener.accept(this.energy);
        }
        return received;
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
        int extracted = super.extractEnergy(toExtract, simulate);
        if (!simulate && extracted > 0) {
            changeListener.accept(this.energy);
        }
        return extracted;
    }

    public void setEnergy(int energy) {
        int clamped = Mth.clamp(energy, 0, this.capacity);
        if (this.energy != clamped) {
            this.energy = clamped;
            changeListener.accept(this.energy);
        }
    }
}
