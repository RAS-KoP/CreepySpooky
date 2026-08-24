package io.github.ras_kop.creepyspooky.block;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.entity.blockEntity.CreativeYoryokuResourceBlockBlockEntity;
import io.github.ras_kop.creepyspooky.register.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CreativeYoryokuResourceBlock extends BaseEntityBlock{

    public static final String BLOCK_ID = "creative_yoryoku_resource_block";

    public CreativeYoryokuResourceBlock(Properties properties) {
        super(properties);
    }
    
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new CreativeYoryokuResourceBlockBlockEntity(arg0, arg1);
    }


    private static final MapCodec<HokoraInterfaceBlock> CODEC =
            simpleCodec(HokoraInterfaceBlock::new);
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
        Level level,
        BlockState state,
        BlockEntityType<T> type) {

        return createTickerHelper(
            type,
            BlockEntityRegister.CREATIVE_YORYOKU_RESOURCE_BLOCK_BLOCKENTITY.get(),
            CreativeYoryokuResourceBlockBlockEntity::tick
        );
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
