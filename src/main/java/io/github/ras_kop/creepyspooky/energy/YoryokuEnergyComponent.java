package io.github.ras_kop.creepyspooky.energy;

import io.github.ras_kop.creepyspooky.api.IYoryokuEnergy;

public class YoryokuEnergyComponent implements IYoryokuEnergy {

    private int yoryoku_amount;
    private int yoryoku_capacity;

    public YoryokuEnergyComponent(int capacity){
        this.yoryoku_capacity = capacity;
    }


    @Override
    public int getYoryoku(){
        return yoryoku_amount;
    }

    @Override
    public int getCapacity(){
        return yoryoku_capacity;
    }

    @Override
    public void setCapacity(int capacity){
        this.yoryoku_capacity = capacity;
    }

    @Override
    public void setYoryoku(int energy){
        this.yoryoku_amount = energy;
    }

    @Override
    public int receiveYoryoku(int amount){
        //容量に追加できたエネルギー量を返す
        int received = Math.min(yoryoku_capacity - yoryoku_amount, amount);
        yoryoku_amount += received;
        return received;
    }

    @Override
    public int extractYoryoku(int amount){
        //出力できたエネルギー量を返す
        int extracted = Math.min(yoryoku_amount, amount);
        yoryoku_amount -= extracted;
        return extracted;
    }
}
