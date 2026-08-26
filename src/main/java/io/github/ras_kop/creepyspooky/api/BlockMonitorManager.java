package io.github.ras_kop.creepyspooky.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class BlockMonitorManager {
    
    private static final  Map<BlockPos, Set<IBlockChangeListener>> listeners = new HashMap<>();

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event){
        
    }
}
