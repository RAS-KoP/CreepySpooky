package io.github.ras_kop.creepyspooky.api;

import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;

public interface IYoryokuHolder {
    
    YoryokuEnergyComponent getEnergyComponent();

    default int getYoryoku(){
        return getEnergyComponent().getYoryoku();
    }

    default int getCapacity(){
        return getEnergyComponent().getCapacity();
    }

    default void setCapacity(int capacity){
        getEnergyComponent().setCapacity(capacity);
    }

    default void setYoryoku(int energy){
        getEnergyComponent().setYoryoku(energy);
    }

    default int receiveYoryoku(int amount){
        //容量に追加できたエネルギー量を返す
        return getEnergyComponent().receiveYoryoku(amount);
    }

    default int extractYoryoku(int amount){
        //出力できたエネルギー量を返す
        return getEnergyComponent().extractYoryoku(amount);
    }
}
