package io.github.ras_kop.creepyspooky.energy;

import io.github.ras_kop.creepyspooky.api.IYoryokuEnergy;

public class YoryokuEnergyComponent implements IYoryokuEnergy {

    private int energy_amount;
    private int energy_capacity;

    public YoryokuEnergyComponent(int capacity){
        this.energy_capacity = capacity;
    }


    @Override
    public int getEnergy(){
        return energy_amount;
    };

    @Override
    public int getCapacity(){
        return energy_capacity;
    }

    @Override
    public void setCapacity(int capacity){
        this.energy_capacity = capacity;
    }

    @Override
    public void setEnergy(int energy){
        this.energy_amount = energy;
    }

    @Override
    public int receiveEnergy(int amount){
        //容量に追加できたエネルギー量を返す
        int received = Math.min(energy_capacity - energy_amount, amount);
        energy_amount += received;
        return received;
    }

    @Override
    public int extractEnergy(int amount){
        //出力できたエネルギー量を返す
        int extracted = Math.min(energy_amount, amount);
        energy_amount -= extracted;
        return extracted;
    }
}
