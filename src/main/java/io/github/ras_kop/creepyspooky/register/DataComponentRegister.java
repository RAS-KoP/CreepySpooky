package io.github.ras_kop.creepyspooky.register;

import javax.annotation.Nonnull;

import io.github.ras_kop.creepyspooky.data.ModDataComponents;
import net.neoforged.bus.api.IEventBus;

public class DataComponentRegister {
    public static void register(@Nonnull IEventBus modEventBus){
        ModDataComponents.register(modEventBus);
    }
}
