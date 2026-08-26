package io.github.ras_kop.creepyspooky.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IBlockChangeListener {
    void announceBlockChanged(Level level, BlockPos pos);
}
