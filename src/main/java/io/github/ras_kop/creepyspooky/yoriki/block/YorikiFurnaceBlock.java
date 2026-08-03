package io.github.ras_kop.creepyspooky.yoriki.block;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.yoriki.block.entity.YorikiFurnaceBlockEntity;
import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class YorikiFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<YorikiFurnaceBlock> CODEC = simpleCodec(YorikiFurnaceBlock::new);

    public YorikiFurnaceBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<YorikiFurnaceBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new YorikiFurnaceBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(
            blockEntityType,
            YorikiBlockEntityRegister.YORIKI_FURNACE.get(),
            YorikiFurnaceBlockEntity::serverTick
        );
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof YorikiFurnaceBlockEntity furnace) {
            player.openMenu((MenuProvider) furnace);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
}
