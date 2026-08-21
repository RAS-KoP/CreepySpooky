package io.github.ras_kop.creepyspooky.renderer;

import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import io.github.ras_kop.creepyspooky.model.HokoraInterfaceBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class HokoraInterfaceBlockRenderer extends GeoBlockRenderer<HokoraMultiblockBlockEntity>{

    public HokoraInterfaceBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new HokoraInterfaceBlockModel());
    }
}
