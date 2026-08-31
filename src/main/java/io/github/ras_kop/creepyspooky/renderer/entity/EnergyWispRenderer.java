package io.github.ras_kop.creepyspooky.renderer.entity;

import io.github.ras_kop.creepyspooky.entity.EnergyWispEntity;
import io.github.ras_kop.creepyspooky.model.entity.EnergyWispModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class EnergyWispRenderer extends GeoEntityRenderer<EnergyWispEntity>{
    public EnergyWispRenderer(EntityRendererProvider.Context context) {
        super(context, new EnergyWispModel());
    }
}
