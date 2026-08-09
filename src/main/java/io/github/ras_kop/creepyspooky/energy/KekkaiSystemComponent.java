package io.github.ras_kop.creepyspooky.energy;

import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;

public class KekkaiSystemComponent implements IYoryokuHolder{
    
    private int kekkai_tier;

    private final YoryokuEnergyComponent yoryoku_energy =
        new YoryokuEnergyComponent(2000);

    @Override
    public YoryokuEnergyComponent getEnergyComponent(){
        return yoryoku_energy;
    }

    public KekkaiSystemComponent(int tier, int capacity){
        this.kekkai_tier = tier;
        this.yoryoku_energy.setCapacity(capacity);
    }

    public int getTier(){
        return kekkai_tier;
    }
}
