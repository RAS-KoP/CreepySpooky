package io.github.ras_kop.creepyspooky.attribute;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EnergyWispAttribute {
    @SuppressWarnings("null")
    public static final DeferredRegister<Attribute> ATTRIBUTES =
        DeferredRegister.create(Registries.ATTRIBUTE, CreepySpooky.MODID);


    public static final DeferredHolder<Attribute, Attribute> YORYOKU_IMPORT_SPEED = 
        ATTRIBUTES.register(
            "yoryoku_import_speed",
            () -> new RangedAttribute(
                "attribute.name.creepyspooky.yoryoku_import_speed",
                10.0, 
                0.0, 
                Double.MAX_VALUE
            ).setSyncable(true)
        );
    
    public static final DeferredHolder<Attribute, Attribute> YORYOKU_EXPORT_SPEED = 
        ATTRIBUTES.register(
            "yoryoku_export_speed",
            () -> new RangedAttribute(
                "attribute.name.creepyspooky.yoryoku_export_speed",
                10.0, 
                0.0, 
                Double.MAX_VALUE
            ).setSyncable(true)
        );
}
