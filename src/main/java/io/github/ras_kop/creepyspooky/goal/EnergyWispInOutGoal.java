package io.github.ras_kop.creepyspooky.goal;

import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity.WorkState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class EnergyWispInOutGoal extends Goal{

    private final EnergyWispEntity mob;

    public EnergyWispInOutGoal(EnergyWispEntity mob){
        this.mob = mob;
    }


    @Override
    public boolean canUse() {
        return mob.getState() == WorkState.InOut;
    }

    @Override
    public void tick() {
        BlockPos target = mob.getTargetBlockPos();
        
    }
}
