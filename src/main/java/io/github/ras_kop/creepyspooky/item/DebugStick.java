package io.github.ras_kop.creepyspooky.item;

import io.github.ras_kop.creepyspooky.api.IKekkaiSystemHolder;
import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import io.github.ras_kop.creepyspooky.register.BlockRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DebugStick extends Item{

    public static final String ITEM_ID = "debug_stick";

    public DebugStick(Properties properties) {
        super(properties);
    }


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

        // ItemStackのCustomDataを取得
        CompoundTag tag = stack
            .getOrDefault(
                DataComponents.CUSTOM_DATA,
                CustomData.EMPTY
            )
            .copyTag();

        int home_x = tag.getInt("Home_X");
        int home_y = tag.getInt("Home_Y");
        int home_z = tag.getInt("Home_Z");
        BlockPos home_pos = new BlockPos(home_x, home_y, home_z);

        int import_x = tag.getInt("Import_X");
        int import_y = tag.getInt("Import_Y");
        int import_z = tag.getInt("Import_Z");
        BlockPos import_pos = new BlockPos(import_x, import_y, import_z);

        int export_x = tag.getInt("Export_X");
        int export_y = tag.getInt("Export_Y");
        int export_z = tag.getInt("Export_Z");
        BlockPos export_pos = new BlockPos(export_x, export_y, export_z);

        player.sendSystemMessage(
            Component.literal("Import: " + "X."+import_x + "Y."+import_y + "Z."+import_z)
        );

        player.sendSystemMessage(
            Component.literal("Home: " + "X."+home_x + "Y."+home_y + "Z."+home_z)
        );

        player.sendSystemMessage(
            Component.literal("Export: " + "X."+export_x + "Y."+export_y + "Z."+export_z)
        );

        // Shiftを押しているか
        if (player.isShiftKeyDown()) {

            // Shift+クリックの場合

            tag.putInt("Export_X", pos.getX());
            tag.putInt("Export_Y", pos.getY());
            tag.putInt("Export_Z", pos.getZ());

            export_x = tag.getInt("Export_X");
            export_y = tag.getInt("Export_Y");
            export_z = tag.getInt("Export_Z");
            export_pos = new BlockPos(export_x, export_y, export_z);

            player.sendSystemMessage(
                Component.literal("Export: " + "X."+export_x + "Y."+export_y + "Z."+export_z)
            );
        } else {

            if(
                home_x + home_y + home_z == 0
                ||
                !level.getBlockState(home_pos).is(BlockRegister.HOKORA_INTERFACE_BLOCK)
            ){

                tag.putInt("Home_X", pos.getX());
                tag.putInt("Home_Y", pos.getY());
                tag.putInt("Home_Z", pos.getZ());

                home_x = tag.getInt("Home_X");
                home_y = tag.getInt("Home_Y");
                home_z = tag.getInt("Home_Z");
                home_pos = new BlockPos(home_x, home_y, home_z);

                player.sendSystemMessage(
                    Component.literal("Home: " + "X."+home_x + "Y."+home_y + "Z."+home_z)
                );
            }else{

                tag.putInt("Import_X", pos.getX());
                tag.putInt("Import_Y", pos.getY());
                tag.putInt("Import_Z", pos.getZ());
            
                import_x = tag.getInt("Import_X");
                import_y = tag.getInt("Import_Y");
                import_z = tag.getInt("Import_Z");
                import_pos = new BlockPos(import_x, import_y, import_z);
            
                player.sendSystemMessage(
                    Component.literal("Import: " + "X."+import_x + "Y."+import_y + "Z."+import_z)
                );
            }
        }

        if(level.getBlockState(home_pos).is(BlockRegister.HOKORA_INTERFACE_BLOCK)){
            player.sendSystemMessage(
                Component.literal("home: OK")
            );

            if(checkKekkaiSystem(level, import_pos)){
                player.sendSystemMessage(
                    Component.literal("import: OK")
                );

                if(checkKekkaiSystem(level, export_pos)){
                    player.sendSystemMessage(
                        Component.literal("export: OK")
                    );

                    BlockEntity entity = level.getBlockEntity(home_pos);
                    if(entity instanceof HokoraMultiblockBlockEntity kekkai){
                        kekkai.spawnTransporter(import_pos, export_pos);
                    }
                }
            }
        }


        // ItemStackに保存
        stack.set(
            DataComponents.CUSTOM_DATA,
            CustomData.of(tag)
        );

        return InteractionResult.SUCCESS;
    }


    private boolean checkKekkaiSystem(Level level, BlockPos pos){

        BlockEntity entity = level.getBlockEntity(pos);

        if (entity instanceof IYoryokuHolder yoryokuHolder){
            return true;
        }
        if (entity instanceof IKekkaiSystemHolder kekkaiHolder){
            return true;
        }
        return false;
    }
}


