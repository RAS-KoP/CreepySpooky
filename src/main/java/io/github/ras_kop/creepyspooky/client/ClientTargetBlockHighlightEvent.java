package io.github.ras_kop.creepyspooky.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.data.ModDataComponents;
import io.github.ras_kop.creepyspooky.register.ItemRegister;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = CreepySpooky.MODID, value = Dist.CLIENT)
public class ClientTargetBlockHighlightEvent {

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {

        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }

        ItemStack stack = minecraft.player.getMainHandItem();

        if (
            !stack.is(ItemRegister.YORYOKU_WAND.get())
        ) {
            return;
        }

        BlockPos home_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_HOME);
        BlockPos import_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_IMPORT);
        BlockPos export_pos = stack.get(ModDataComponents.TARGET_BLOCK_POS_EXPORT);

        MultiBufferSource.BufferSource buffer = minecraft.renderBuffers().bufferSource();
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.lines());

        PoseStack poseStack = event.getPoseStack();
        Camera camera = minecraft.gameRenderer.getMainCamera();

        if (home_pos != null) {

            poseStack.pushPose();

            // カメラを基準にした座標へ変換
            double x = home_pos.getX() - camera.getPosition().x;
            double y = home_pos.getY() - camera.getPosition().y;
            double z = home_pos.getZ() - camera.getPosition().z;

            poseStack.translate(x, y, z);


            // ブロック1個分の枠を描画
            LevelRenderer.renderLineBox(
                poseStack,
                vertexConsumer,
                0.0, 0.0, 0.0,
                1.0, 1.0, 1.0,
                1.0F, 0.0F, 0.0F,
                1.0F
            );

            poseStack.popPose();
        }

        if(import_pos != null){
            poseStack.pushPose();

            // カメラを基準にした座標へ変換
            double x = import_pos.getX() - camera.getPosition().x;
            double y = import_pos.getY() - camera.getPosition().y;
            double z = import_pos.getZ() - camera.getPosition().z;

            poseStack.translate(x, y, z);

            // ブロック1個分の枠を描画
            LevelRenderer.renderLineBox(
                poseStack,
                vertexConsumer,
                0.0, 0.0, 0.0,
                1.0, 1.0, 1.0,
                0.0F, 1.0F, 0.0F,
                1.0F
            );

            poseStack.popPose();
        }

        if(export_pos != null){
            poseStack.pushPose();

            // カメラを基準にした座標へ変換
            double x = export_pos.getX() - camera.getPosition().x;
            double y = export_pos.getY() - camera.getPosition().y;
            double z = export_pos.getZ() - camera.getPosition().z;

            poseStack.translate(x, y, z);

            // ブロック1個分の枠を描画
            LevelRenderer.renderLineBox(
                poseStack,
                vertexConsumer,
                0.0, 0.0, 0.0,
                1.0, 1.0, 1.0,
                0.0F, 0.0F, 1.0F,
                1.0F
            );

            poseStack.popPose();
        }



        buffer.endBatch(RenderType.lines());
    }
}
