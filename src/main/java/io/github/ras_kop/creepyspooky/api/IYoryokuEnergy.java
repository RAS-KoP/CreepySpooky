package io.github.ras_kop.creepyspooky.api;

public interface IYoryokuEnergy {
    
    int getEnergy();

    int getCapacity();

    void setCapacity(int capacity);

    void setEnergy(int energy);

    int receiveEnergy(int amount);

    int extractEnergy(int amount);
}
