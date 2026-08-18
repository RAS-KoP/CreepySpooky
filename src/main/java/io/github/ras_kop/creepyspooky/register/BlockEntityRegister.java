package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityRegister {

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIY_TYPES.register(eventBus);
    }

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIY_TYPES =
        DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE,
            CreepySpooky.MODID
        );
    
}
