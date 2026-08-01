package io.github.ras_kop.creepyspooky.model;


import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.entity.TestEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import net.minecraft.resources.ResourceLocation;

public class TestEntityModel
        extends DefaultedEntityGeoModel<TestEntity> {

    public TestEntityModel() {
        super(ResourceLocation.fromNamespaceAndPath(
                CreepySpooky.MODID,
                TestEntity.ENTITY_ID));
    }
}