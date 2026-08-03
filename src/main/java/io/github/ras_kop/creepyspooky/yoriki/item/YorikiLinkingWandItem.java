package io.github.ras_kop.creepyspooky.yoriki.item;

import javax.annotation.Nullable;

import io.github.ras_kop.creepyspooky.yoriki.block.entity.YorikiReceiverBlockEntity;
import io.github.ras_kop.creepyspooky.yoriki.block.entity.YorikiTransmitterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class YorikiLinkingWandItem extends Item {
    private static final String SELECTED_POS = "YorikiSelectedTransmitterPos";
    private static final String SELECTED_DIMENSION = "YorikiSelectedTransmitterDimension";

    public YorikiLinkingWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ItemStack wand = context.getItemInHand();
        BlockPos clickedPos = context.getClickedPos();
        BlockEntity blockEntity = level.getBlockEntity(clickedPos);

        if (blockEntity instanceof YorikiTransmitterBlockEntity transmitter) {
            if (context.isSecondaryUseActive()) {
                clearConnection(level, transmitter);
                player.displayClientMessage(Component.translatable("message.creepyspooky.yoriki_connection_cleared"), true);
            } else {
                selectTransmitter(wand, level, clickedPos);
                player.displayClientMessage(Component.translatable("message.creepyspooky.yoriki_transmitter_selected"), true);
            }
            return InteractionResult.CONSUME;
        }

        if (blockEntity instanceof YorikiReceiverBlockEntity receiver) {
            if (context.isSecondaryUseActive()) {
                clearConnection(level, receiver);
                player.displayClientMessage(Component.translatable("message.creepyspooky.yoriki_connection_cleared"), true);
                return InteractionResult.CONSUME;
            }

            SelectedTransmitter selected = getSelectedTransmitter(wand);
            if (selected == null) {
                player.displayClientMessage(Component.translatable("message.creepyspooky.yoriki_select_transmitter_first"), true);
                return InteractionResult.CONSUME;
            }
            if (!selected.dimension().equals(level.dimension().location().toString())) {
                player.displayClientMessage(Component.translatable("message.creepyspooky.yoriki_same_dimension_required"), true);
                return InteractionResult.CONSUME;
            }

            BlockEntity selectedEntity = level.getBlockEntity(selected.pos());
            if (!(selectedEntity instanceof YorikiTransmitterBlockEntity transmitter)) {
                clearSelectedTransmitter(wand);
                player.displayClientMessage(Component.translatable("message.creepyspooky.yoriki_transmitter_missing"), true);
                return InteractionResult.CONSUME;
            }

            clearConnection(level, transmitter);
            clearConnection(level, receiver);
            transmitter.setLinkedReceiver(level, clickedPos);
            receiver.setLinkedTransmitter(level, selected.pos());
            clearSelectedTransmitter(wand);
            player.displayClientMessage(Component.translatable("message.creepyspooky.yoriki_connection_registered"), true);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    private static void selectTransmitter(ItemStack wand, Level level, BlockPos pos) {
        CustomData.update(DataComponents.CUSTOM_DATA, wand, tag -> {
            tag.putLong(SELECTED_POS, pos.asLong());
            tag.putString(SELECTED_DIMENSION, level.dimension().location().toString());
        });
    }

    @Nullable
    private static SelectedTransmitter getSelectedTransmitter(ItemStack wand) {
        CompoundTag tag = wand.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (!tag.contains(SELECTED_POS, Tag.TAG_LONG) || !tag.contains(SELECTED_DIMENSION, Tag.TAG_STRING)) {
            return null;
        }
        return new SelectedTransmitter(BlockPos.of(tag.getLong(SELECTED_POS)), tag.getString(SELECTED_DIMENSION));
    }

    private static void clearSelectedTransmitter(ItemStack wand) {
        CustomData data = wand.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();
        tag.remove(SELECTED_POS);
        tag.remove(SELECTED_DIMENSION);
        CustomData.set(DataComponents.CUSTOM_DATA, wand, tag);
    }

    private static void clearConnection(Level level, YorikiTransmitterBlockEntity transmitter) {
        BlockPos receiverPos = transmitter.getLinkedReceiver();
        if (receiverPos != null && level.getBlockEntity(receiverPos) instanceof YorikiReceiverBlockEntity receiver) {
            receiver.clearLinkedTransmitter();
        }
        transmitter.clearLinkedReceiver();
    }

    private static void clearConnection(Level level, YorikiReceiverBlockEntity receiver) {
        BlockPos transmitterPos = receiver.getLinkedTransmitter();
        if (transmitterPos != null && level.getBlockEntity(transmitterPos) instanceof YorikiTransmitterBlockEntity transmitter) {
            transmitter.clearLinkedReceiver();
        }
        receiver.clearLinkedTransmitter();
    }

    private record SelectedTransmitter(BlockPos pos, String dimension) {
    }
}
