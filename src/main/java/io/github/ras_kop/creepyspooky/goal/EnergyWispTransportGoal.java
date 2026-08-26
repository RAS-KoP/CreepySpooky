package io.github.ras_kop.creepyspooky.goal;

import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity.BlockWorkRole;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity.WorkState;
import net.minecraft.core.BlockPos;
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
        BlockPos target = mob.getTargetBlockPos().getValue();
        
        Vec3 direction = Vec3.atCenterOf(target).subtract(mob.position());

        if(direction.length() <= 0.3){
            
            if(mob.getTargetBlockPos().getKey() == BlockWorkRole.HOME){
                //mob.nextTarget();
                mob.setState(WorkState.Idle);
                return;
            }
            mob.setState(WorkState.InOut);
            return;
        }

        mob.setDeltaMovement(
            direction.normalize().scale(mob.getAttributeValue(Attributes.MOVEMENT_SPEED))
        );

    }
    
}
