package io.github.ras_kop.creepyspooky.api;

public interface IYoryokuEnergy {
    
    int getYoryoku();

    int getCapacity();

    void setCapacity(int capacity);

    void setYoryoku(int energy);

    int receiveYoryoku(int amount);

    int extractYoryoku(int amount);
}
