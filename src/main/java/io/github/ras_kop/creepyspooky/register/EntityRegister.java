package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import io.github.ras_kop.creepyspooky.entity.TestEntity;
import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityRegister {
    
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(
            Registries.ENTITY_TYPE, 
            CreepySpooky.MODID
        );

    public static final DeferredHolder<EntityType<?>, EntityType<TestEntity>> TEST_ENTITY = 
        ENTITY_TYPES.register(
            TestEntity.ENTITY_ID,
            () -> EntityType.Builder.<TestEntity>of(
                TestEntity::new,
                MobCategory.CREATURE
            )
            .sized(1.0F, 1.0F)
            .build(TestEntity.ENTITY_ID)
        );

    public static final DeferredHolder<EntityType<?>, EntityType<EnergyWispEntity>> ENERGY_WISP = 
        ENTITY_TYPES.register(
            EnergyWispEntity.ENTITY_ID,
            () -> EntityType.Builder.<EnergyWispEntity>of(
                EnergyWispEntity::new,
                MobCategory.CREATURE
            )
            .sized(1.0F, 1.0F)
            .build(EnergyWispEntity.ENTITY_ID)
        );


    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
            EntityRegister.TEST_ENTITY.get(),
            TestEntity.createAttributes().build()
        );

        event.put(
            EntityRegister.ENERGY_WISP.get(),
            EnergyWispEntity.createAttributes().build()
        );
    }


    //ここからブロックエンティティ系

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
    
}
