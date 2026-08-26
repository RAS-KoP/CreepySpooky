package io.github.ras_kop.creepyspooky.renderer.item;

import io.github.ras_kop.creepyspooky.item.YoryokuWand;
import io.github.ras_kop.creepyspooky.model.item.YoryokuWandModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class YoryokuWandRenderer extends GeoItemRenderer<YoryokuWand> {
    
    public YoryokuWandRenderer() {
        super(new YoryokuWandModel());
    }

}
