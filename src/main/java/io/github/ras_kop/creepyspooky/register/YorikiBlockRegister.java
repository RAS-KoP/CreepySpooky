package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.yoriki.block.CreativeYorikiPoolBlock;
import io.github.ras_kop.creepyspooky.yoriki.block.YorikiFurnaceBlock;
import io.github.ras_kop.creepyspooky.yoriki.block.YorikiReceiverBlock;
import io.github.ras_kop.creepyspooky.yoriki.block.YorikiTransmitterBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class YorikiBlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreepySpooky.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreepySpooky.MODID);

    public static final DeferredBlock<Block> CREATIVE_YORIKI_POOL = BLOCKS.register(
        "creative_yoriki_pool",
        () -> new CreativeYorikiPoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(3.5F))
    );
    public static final DeferredBlock<Block> YORIKI_TRANSMITTER = BLOCKS.register(
        "yoriki_transmitter",
        () -> new YorikiTransmitterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F))
    );
    public static final DeferredBlock<Block> YORIKI_RECEIVER = BLOCKS.register(
        "yoriki_receiver",
        () -> new YorikiReceiverBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.5F))
    );
    public static final DeferredBlock<Block> YORIKI_FURNACE = BLOCKS.register(
        "yoriki_furnace",
        () -> new YorikiFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.5F))
    );

    public static final DeferredItem<BlockItem> CREATIVE_YORIKI_POOL_ITEM = ITEMS.registerSimpleBlockItem("creative_yoriki_pool", CREATIVE_YORIKI_POOL);
    public static final DeferredItem<BlockItem> YORIKI_TRANSMITTER_ITEM = ITEMS.registerSimpleBlockItem("yoriki_transmitter", YORIKI_TRANSMITTER);
    public static final DeferredItem<BlockItem> YORIKI_RECEIVER_ITEM = ITEMS.registerSimpleBlockItem("yoriki_receiver", YORIKI_RECEIVER);
    public static final DeferredItem<BlockItem> YORIKI_FURNACE_ITEM = ITEMS.registerSimpleBlockItem("yoriki_furnace", YORIKI_FURNACE);

    private YorikiBlockRegister() {
    }
}
