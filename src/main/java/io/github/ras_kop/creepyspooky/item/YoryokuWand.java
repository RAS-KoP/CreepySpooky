package io.github.ras_kop.creepyspooky.item;

import java.util.List;
import java.util.function.Consumer;

import io.github.ras_kop.creepyspooky.api.IKekkaiSystemHolder;
import io.github.ras_kop.creepyspooky.api.IYoryokuHolder;
import io.github.ras_kop.creepyspooky.data.ModDataComponents;
import io.github.ras_kop.creepyspooky.entity.blockEntity.HokoraMultiblockBlockEntity;
import io.github.ras_kop.creepyspooky.register.BlockRegister;
import io.github.ras_kop.creepyspooky.renderer.item.YoryokuWandRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtil;


public class YoryokuWand extends Item implements GeoItem {


    private final AnimatableInstanceCache cache =
            GeckoLibUtil.createInstanceCache(this);

    public YoryokuWand(Properties properties) {
        super(properties);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(
            new AnimationController<>(
                this,
                "Walking",
                5,
                state -> {
                    // 現在のItemDisplayContextを取得
                    ItemDisplayContext perspective =
                            state.getData(DataTickets.ITEM_RENDER_PERSPECTIVE);

                    // 手に持って表示されているときだけアニメーション
                    boolean isHeld =
                            perspective == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND ||
                            perspective == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ||
                            perspective == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND ||
                            perspective == ItemDisplayContext.THIRD_PERSON_LEFT_HAND;

                    if (!isHeld) {
                        return PlayState.STOP;
                    }

                    LocalPlayer player = Minecraft.getInstance().player;

                    if (player == null) {
                        return PlayState.STOP;
                    }

                    boolean isMoving =
                            player.getDeltaMovement()
                                .horizontalDistanceSqr() > 0.0001;

                    return state.setAndContinue(
                        isMoving
                            ? DefaultAnimations.WALK
                            : DefaultAnimations.IDLE
                    );
                }
            )
        );
    }

    @Override
    public boolean isPerspectiveAware() {
        return true;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(
            new GeoRenderProvider() {
                
                private final YoryokuWandRenderer renderer = new YoryokuWandRenderer();

                @Override
                public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                    return renderer;
                }
            }
        );
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtil.getCurrentSystemTick();
    }






    //・・・ここから下UjitaR変更

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

            if (checkKekkaiSystem(level, import_pos)) {

                if (checkKekkaiSystem(level, export_pos)) {

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
