package io.github.ras_kop.creepyspooky.register;

import javax.annotation.Nonnull;

import io.github.ras_kop.creepyspooky.date.ModDateComponents;
import net.neoforged.bus.api.IEventBus;

public class DateComponentRegister {
    public static void register(@Nonnull IEventBus modEventBus){
        ModDateComponents.register(modEventBus);
    }
}
