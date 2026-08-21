package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.block.HokoraInterfaceBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegister {
    private static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(Registries.BLOCK, CreepySpooky.MODID);
    
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreepySpooky.MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
    }
        
    //public static final DeferredHolder<Block, Block> EXAMPLE_BLOCK = 
    //    BLOCKS.register("example_block", ExampleBlock::new);
    

    //public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ExampleBlockEntity>> EXAMPLE_BLOCK_ENTITY = 
    //    BLOCK_ENTITIES.register("example", () -> BlockEntityType.Builder.of(ExampleBlockEntity::new, EXAMPLE_BLOCK_ENTITY.get()).build(null));

    public static final DeferredHolder<Block, Block> EXAMPLE_BLOCK = 
        BLOCKS.register(
            "example_block", 
            () -> new Block(
                BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
        );


    public static final DeferredHolder<Block, Block> HOKORA_INTERFACE_BLOCK =
        BLOCKS.register(
            HokoraInterfaceBlock.BLOCK_ID,
            () -> new HokoraInterfaceBlock(
                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).noOcclusion()
            )
        );
}
