package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.entity.blockEntity.CreativeYoryokuResourceBlockBlockEntity;
import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
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

    public static final DeferredHolder<
            BlockEntityType<?>, 
            BlockEntityType<HokoraMultiblockBlockEntity>
        > HOKORA_MULTIBLOCK_BLOCKENTITY = BLOCK_ENTITIY_TYPES.register(
            HokoraMultiblockBlockEntity.ENTITY_ID,
            () -> BlockEntityType.Builder.of(
                HokoraMultiblockBlockEntity::new,
                BlockRegister.HOKORA_INTERFACE_BLOCK.get()
            ).build(null)
        );
    

    public static final DeferredHolder<
            BlockEntityType<?>, 
            BlockEntityType<CreativeYoryokuResourceBlockBlockEntity>
        > CREATIVE_YORYOKU_RESOURCE_BLOCK_BLOCKENTITY = BLOCK_ENTITIY_TYPES.register(
            CreativeYoryokuResourceBlockBlockEntity.ENTITY_ID,
            () -> BlockEntityType.Builder.of(
                CreativeYoryokuResourceBlockBlockEntity::new,
                BlockRegister.CREATIVE_YORYOKU_RESOURCE_BLOCK.get()
            ).build(null)
        );
}
