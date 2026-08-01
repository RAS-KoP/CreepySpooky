package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.entity.TestEntity;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class AttributesRegister {
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
            EntityRegister.TEST_ENTITY.get(),
            TestEntity.createAttributes().build()
        );
    }
}
