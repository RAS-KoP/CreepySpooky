package io.github.ras_kop.creepyspooky.energy;

import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import net.minecraft.world.level.block.entity.BlockEntity;

public class KekkaiSystemComponent implements IYoryokuHolder{
    
    private int kekkai_tier;
    private BlockEntity block_entity;

    private final YoryokuEnergyComponent yoryoku_energy =
        new YoryokuEnergyComponent(2000);

    @Override
    public YoryokuEnergyComponent getYoryokuComponent(){
        return yoryoku_energy;
    }

    public KekkaiSystemComponent(BlockEntity entity, int tier, int capacity){
        block_entity = entity;
        this.kekkai_tier = tier;
        this.yoryoku_energy.setCapacity(capacity);
    }

    public int getTier(){
        return kekkai_tier;
    }

    public void setTier(int tier){
        kekkai_tier = tier;
    }

    @Override
    public void updateEntity() {
        block_entity.setChanged();
    }
}
