package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.attribute.EnergyWispAttribute;
import net.neoforged.bus.api.IEventBus;

public class AttributesRegister {
    public static void register(IEventBus modEventBus){
        EnergyWispAttribute.ATTRIBUTES.register(modEventBus);
    }
}
