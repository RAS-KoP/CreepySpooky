package io.github.ras_kop.creepyspooky.block;

import com.mojang.serialization.MapCodec;

import io.github.ras_kop.creepyspooky.entity.blockEntity.CreativeYoryokuResourceBlockBlockEntity;
import io.github.ras_kop.creepyspooky.register.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

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

            if (blockEntity instanceof CreativeYoryokuResourceBlockBlockEntity resource) {
                player.sendSystemMessage(
                    Component.literal("Energy: " + resource.getYoryoku()+"/"+ resource.getCapacity())
                );
            }
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

}
