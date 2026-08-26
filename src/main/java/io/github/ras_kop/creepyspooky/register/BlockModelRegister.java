package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.renderer.block.HokoraInterfaceBlockRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class BlockModelRegister {
    public static void register(EntityRenderersEvent.RegisterRenderers event) {

        event.registerBlockEntityRenderer(
            BlockEntityRegister.HOKORA_MULTIBLOCK_BLOCKENTITY.get(),
            HokoraInterfaceBlockRenderer::new
        );
    }
}
