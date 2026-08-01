package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.renderer.TestEntityRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class EntityModelRegister {
    
    public static void register(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(
            EntityRegister.TEST_ENTITY.get(),
            TestEntityRenderer::new
        );
    }
}
