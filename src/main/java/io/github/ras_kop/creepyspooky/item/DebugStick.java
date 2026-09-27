package io.github.ras_kop.creepyspooky.item;

import java.util.List;

import io.github.ras_kop.creepyspooky.api.IKekkaiSystemHolder;
import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.data.ModDataComponents;
import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import io.github.ras_kop.creepyspooky.register.BlockRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DebugStick extends Item {

    public static final String ITEM_ID = "debug_stick";

    public DebugStick(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("null")
    @Override
    public InteractionResult useOn(UseOnContext context) {

        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        Level level = context.getLevel();

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (player == null) {
            return InteractionResult.PASS;
        }

        BlockPos home_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_HOME);
        BlockPos import_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_IMPORT);
        BlockPos export_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_EXPORT);

        // Shiftを押しているか
        if (player.isShiftKeyDown()) {

            // Shift+クリックの場合
            home_pos = pos;
            stack.set(ModDataComponents.TARGET_BLOCK_POS_HOME, home_pos);

            player.sendSystemMessage(
                Component.literal(
                    "HOME: " + "X." + pos.getX() + "Y." + pos.getY() + "Z." + pos.getZ()
                )
            );
        } else {

            if (import_pos == null) {
                import_pos = pos;
                stack.set(ModDataComponents.TARGET_BLOCK_POS_IMPORT, import_pos);

                player.sendSystemMessage(
                    Component.literal(
                        "IMPORT: " + "X." + pos.getX() + "Y." + pos.getY() + "Z." + pos.getZ()
                    )
                );
            } else {
                export_pos = pos;
                stack.set(ModDataComponents.TARGET_BLOCK_POS_EXPORT, export_pos);

                player.sendSystemMessage(
                    Component.literal(
                        "EXPORT: " + "X." + pos.getX() + "Y." + pos.getY() + "Z." + pos.getZ()
                    )
                );
            }

        }

        if (level.getBlockState(home_pos).is(BlockRegister.HOKORA_INTERFACE_BLOCK)) {
            player.sendSystemMessage(
                Component.literal("home: OK")
            );

            if (checkKekkaiSystem(level, import_pos)) {
                player.sendSystemMessage(
                    Component.literal("import: OK")
                );

                if (checkKekkaiSystem(level, export_pos)) {
                    player.sendSystemMessage(
                            Component.literal("export: OK"));

                    BlockEntity entity = level.getBlockEntity(home_pos);
                    if (entity instanceof HokoraMultiblockBlockEntity kekkai) {
                        kekkai.spawnTransporter(import_pos, export_pos);
                        stack.remove(ModDataComponents.TARGET_BLOCK_POS_IMPORT);
                        stack.remove(ModDataComponents.TARGET_BLOCK_POS_EXPORT);
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            Item.TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag
    ){
        super.appendHoverText(
                stack,
                context,
                tooltipComponents,
                tooltipFlag
        );

        BlockPos home_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_HOME);
        BlockPos import_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_IMPORT);
        BlockPos export_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_EXPORT);

        if(home_pos == null){
            tooltipComponents.add(
                Component.literal("HOME: NULL")
            );
        }else{
            tooltipComponents.add(
                Component.literal("HOME: "+home_pos.getX()+","+home_pos.getY()+","+home_pos.getZ())
            );
        }

        if(import_pos == null){
            tooltipComponents.add(
                Component.literal("IMPORT: NULL")
            );
        }else{
            tooltipComponents.add(
                Component.literal("IMPORT: "+import_pos.getX()+","+import_pos.getY()+","+import_pos.getZ())
            );
        }

        if(export_pos == null){
            tooltipComponents.add(
                Component.literal("EXPORT: NULL")
            );
        }else{
            tooltipComponents.add(
                Component.literal("EXPORT: "+export_pos.getX()+","+export_pos.getY()+","+export_pos.getZ())
            );
        }
    }

    private boolean checkKekkaiSystem(Level level, BlockPos pos) {

        BlockEntity entity = level.getBlockEntity(pos);

        if (entity instanceof @SuppressWarnings("unused") IYoryokuHolder yoryokuHolder) {
            return true;
        }
        if (entity instanceof @SuppressWarnings("unused") IKekkaiSystemHolder kekkaiHolder) {
            return true;
        }
        return false;
    }
}
