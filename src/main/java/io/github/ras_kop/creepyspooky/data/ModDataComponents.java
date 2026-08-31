package io.github.ras_kop.creepyspooky.data;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {

    public static void register(@Nonnull IEventBus modEventBus){
        DATA_COMPONENTS.register(modEventBus);
    }
    
    @SuppressWarnings("null")
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
        DeferredRegister.createDataComponents(
            Registries.DATA_COMPONENT_TYPE,
            CreepySpooky.MODID
        );


    @SuppressWarnings("null")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> YORYOKU_ENERGY =
        DATA_COMPONENTS.registerComponentType(
            "yoryoku_energy",
            builder -> builder.persistent(Codec.INT)
        );


    @SuppressWarnings("null")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> STORED_BLOCK_POS =
        DATA_COMPONENTS.registerComponentType(
            "stored_block_pos",
            builder -> builder.persistent(BlockPos.CODEC)
        );
}
