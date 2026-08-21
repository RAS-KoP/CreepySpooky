package io.github.ras_kop.creepyspooky.block;


import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class HokoraInterfaceBlock extends BaseEntityBlock{

    public static final String BLOCK_ID = "hokora_interface_block";

    private static final MapCodec<HokoraInterfaceBlock> CODEC =
            simpleCodec(HokoraInterfaceBlock::new);

    private static final DirectionProperty FACING =
        BlockStateProperties.HORIZONTAL_FACING;

    public HokoraInterfaceBlock(Properties properties) {
        super(properties);

        registerDefaultState(
            stateDefinition.any().setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new HokoraMultiblockBlockEntity(arg0, arg1);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public ItemInteractionResult useItemOn(
        ItemStack stack,
        BlockState state,
        Level level,
        BlockPos pos,
        Player player,
        InteractionHand hand,
        BlockHitResult hitResult
    ){

        if (!level.isClientSide && stack.is(Items.STICK)) {

            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof HokoraMultiblockBlockEntity hokora) {
                player.sendSystemMessage(
                    Component.literal("Energy: " + hokora.multiBlock_flag)
                );
            }
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }


    @Override
    protected void neighborChanged(
        BlockState state,
        Level level,
        BlockPos pos,
        Block neighborBlock,
        BlockPos neighborPos,
        boolean movedByPiston
    ) {
        super.neighborChanged(
            state,
            level,
            pos,
            neighborBlock,
            neighborPos,
            movedByPiston
        );

        System.out.println("block changed");

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof HokoraMultiblockBlockEntity hokora) {
            hokora.checkMultiblock();
        }
        
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return defaultBlockState().setValue(
                FACING,
                context.getHorizontalDirection()
            );
    }
}
