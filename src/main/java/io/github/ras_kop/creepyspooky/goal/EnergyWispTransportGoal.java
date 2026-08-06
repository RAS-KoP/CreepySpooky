package io.github.ras_kop.creepyspooky.goal;

import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class EnergyWispTransportGoal extends Goal {

    private final EnergyWispEntity mob;

    public EnergyWispTransportGoal(EnergyWispEntity mob){
        this.mob = mob;
    }


    @Override
    public boolean canUse() {
        return true;
    }
    
}
