package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.block.CreativeYorikiPoolBlock;
import io.github.ras_kop.creepyspooky.block.YorikiCableBlock;
import io.github.ras_kop.creepyspooky.block.YorikiFurnaceBlock;
import io.github.ras_kop.creepyspooky.block.YorikiReceiverBlock;
import io.github.ras_kop.creepyspooky.block.YorikiTransmitterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreepySpooky.MODID);

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock(
        "example_block",
        BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
    );

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
    public static final DeferredBlock<Block> YORIKI_CABLE = BLOCKS.register(
        "yoriki_cable",
        () -> new YorikiCableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F))
    );

    private BlockRegister() {
    }
}
