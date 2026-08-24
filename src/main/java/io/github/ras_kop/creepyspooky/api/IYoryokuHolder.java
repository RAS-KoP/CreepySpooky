package io.github.ras_kop.creepyspooky.api;

import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;

public interface IYoryokuHolder {
    
    YoryokuEnergyComponent getYoryokuComponent();

    default int getYoryoku(){
        return getYoryokuComponent().getYoryoku();
    }

    default int getCapacity(){
        return getYoryokuComponent().getCapacity();
    }

    default void setCapacity(int capacity){
        getYoryokuComponent().setCapacity(capacity);
    }

    default void setYoryoku(int energy){
        getYoryokuComponent().setYoryoku(energy);
    }

    default int receiveYoryoku(int amount){
        //容量に追加できたエネルギー量を返す
        return getYoryokuComponent().receiveYoryoku(amount);
    }

    default int extractYoryoku(int amount){
        //出力できたエネルギー量を返す
        return getYoryokuComponent().extractYoryoku(amount);
    }
}
