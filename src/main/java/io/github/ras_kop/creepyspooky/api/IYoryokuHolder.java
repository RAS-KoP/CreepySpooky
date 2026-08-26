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
        updateEntity();
    }

    default void setYoryoku(int energy){
        getYoryokuComponent().setYoryoku(energy);
        updateEntity();
    }

    default int receiveYoryoku(int amount){
        //容量に追加できたエネルギー量を返す
        int tmp = getYoryokuComponent().receiveYoryoku(amount);
        updateEntity();
        return tmp;
    }

    default int extractYoryoku(int amount){
        //出力できたエネルギー量を返す
        int tmp = getYoryokuComponent().extractYoryoku(amount);
        updateEntity();
        return tmp;
    }

    default void updateEntity(){

    }
}
