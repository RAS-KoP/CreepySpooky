package io.github.ras_kop.creepyspooky.goal;

import io.github.ras_kop.creepyspooky.api.IKekkaiSystemHolder;
import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.api.YoryokuTransferMethod;
import io.github.ras_kop.creepyspooky.attribute.EnergyWispAttribute;
import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity.BlockWorkRole;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity.WorkState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

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
        BlockWorkRole role = mob.getTargetBlockPos().getKey();
        BlockPos target = mob.getTargetBlockPos().getValue();

        if(
            Vec3.atCenterOf(target)
            .subtract(mob.position()).length() > 0.3
        ){
            mob.setState(WorkState.Transport);
        }
        
        YoryokuEnergyComponent target_component = getYoryokuHolder(target);
        if(target_component == null){
            mob.targetSetHome();
            return;
        }

        boolean continue_flag = false;
        if(role == BlockWorkRole.IMPORT){
            continue_flag = YoryokuTransferMethod.Transport(
                    target_component,
                    mob.getYoryokuComponent(),
                    (int)mob.getAttribute(EnergyWispAttribute.YORYOKU_IMPORT_SPEED).getValue()
                );
        }
        if(role == BlockWorkRole.EXPORT){
            continue_flag = YoryokuTransferMethod.Transport(
                    mob.getYoryokuComponent(),
                    target_component,
                    (int)mob.getAttribute(EnergyWispAttribute.YORYOKU_IMPORT_SPEED).getValue()
                );
        }

        if(!continue_flag){
            mob.nextTarget();
            mob.setState(WorkState.Transport);
        }
    }

    private YoryokuEnergyComponent getYoryokuHolder(BlockPos target){
        if (target instanceof IYoryokuHolder yoryokuHolder){
            return yoryokuHolder.getYoryokuComponent();
        }
        if(target instanceof IKekkaiSystemHolder kekkaiSystemHolder) {
            return kekkaiSystemHolder.getKekkaiSystemComponent().getYoryokuComponent();
        }
        return null;
    }
}
