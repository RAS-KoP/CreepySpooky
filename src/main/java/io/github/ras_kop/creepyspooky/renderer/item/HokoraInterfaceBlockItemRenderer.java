package io.github.ras_kop.creepyspooky.renderer.item;

import io.github.ras_kop.creepyspooky.item.HokoraInterfaceBlockItem;
import io.github.ras_kop.creepyspooky.model.item.HokoraInterfaceBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HokoraInterfaceBlockItemRenderer extends GeoItemRenderer<HokoraInterfaceBlockItem>{

    public HokoraInterfaceBlockItemRenderer() {
        super(new HokoraInterfaceBlockItemModel());
    }
}
