package io.github.ras_kop.creepyspooky.renderer;

import io.github.ras_kop.creepyspooky.entity.TestEntity;
import io.github.ras_kop.creepyspooky.model.TestEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;



public class TestEntityRenderer extends GeoEntityRenderer<TestEntity> {
    public TestEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new TestEntityModel());
    }
}