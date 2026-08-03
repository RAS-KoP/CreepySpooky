package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.block.entity.YorikiCableBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.CreativeYorikiPoolBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.YorikiFurnaceBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.YorikiReceiverBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.YorikiTransmitterBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class YorikiBlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(
        Registries.BLOCK_ENTITY_TYPE,
        CreepySpooky.MODID
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CreativeYorikiPoolBlockEntity>> CREATIVE_YORIKI_POOL = BLOCK_ENTITY_TYPES.register(
        "creative_yoriki_pool",
        () -> BlockEntityType.Builder.of(CreativeYorikiPoolBlockEntity::new, BlockRegister.CREATIVE_YORIKI_POOL.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<YorikiTransmitterBlockEntity>> YORIKI_TRANSMITTER = BLOCK_ENTITY_TYPES.register(
        "yoriki_transmitter",
        () -> BlockEntityType.Builder.of(YorikiTransmitterBlockEntity::new, BlockRegister.YORIKI_TRANSMITTER.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<YorikiReceiverBlockEntity>> YORIKI_RECEIVER = BLOCK_ENTITY_TYPES.register(
        "yoriki_receiver",
        () -> BlockEntityType.Builder.of(YorikiReceiverBlockEntity::new, BlockRegister.YORIKI_RECEIVER.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<YorikiFurnaceBlockEntity>> YORIKI_FURNACE = BLOCK_ENTITY_TYPES.register(
        "yoriki_furnace",
        () -> BlockEntityType.Builder.of(YorikiFurnaceBlockEntity::new, BlockRegister.YORIKI_FURNACE.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<YorikiCableBlockEntity>> YORIKI_CABLE = BLOCK_ENTITY_TYPES.register(
        "yoriki_cable",
        () -> BlockEntityType.Builder.of(YorikiCableBlockEntity::new, BlockRegister.YORIKI_CABLE.get()).build(null)
    );

    private YorikiBlockEntityRegister() {
    }
}
