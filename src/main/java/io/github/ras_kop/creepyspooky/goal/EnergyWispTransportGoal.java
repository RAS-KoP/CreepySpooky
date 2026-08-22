package io.github.ras_kop.creepyspooky.goal;

import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity.WorkState;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class EnergyWispTransportGoal extends Goal {

    private final EnergyWispEntity mob;

    public EnergyWispTransportGoal(EnergyWispEntity mob){
        this.mob = mob;
    }


    @Override
    public boolean canUse() {
        return mob.getState() == WorkState.Transport;
    }


    @Override
    public void tick() {        
        
        Vec3 direction = 
            Vec3.atCenterOf(mob.getTargetBlockPos())
                .subtract(mob.position());

        mob.setDeltaMovement(direction.normalize().scale( mob.getAttributeValue(Attributes.MOVEMENT_SPEED) ));

        if(direction.length() <= 0.3){
            mob.setState(WorkState.InOut);
        }
    }
    
}
