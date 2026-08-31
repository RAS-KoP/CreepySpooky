package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.block.CreepyFurnacePart;
import io.github.ras_kop.creepyspooky.block.CreativeYoryokuResourceBlock;
import io.github.ras_kop.creepyspooky.block.HokoraInterfaceBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
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

    //かまどブロックの登録
    public static final DeferredHolder<Block,Block> CREEPY_FURNACE =
        BLOCKS.register(
                "creepy_furnace",
                () -> new new CreepyFurnace(
                        BlockBehaviour.Properties.of()
                                .mapColor(MapColor.STONE)
                                .strength(3.0F)
                                .noOcclusion()
                )
        );

    // かまどの透明な構成ブロックを登録
    public static final DeferredHolder<Block, Block> CREEPY_FURNACE_PART =
        BLOCKS.register(
            "creepy_furnace_part",
            () -> new CreepyFurnacePart(
                BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(3.0F)
                    .noOcclusion()
                    .noLootTable()
                    .noTerrainParticles()
            )
        );

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

    public static final DeferredHolder<Block, Block> CREATIVE_YORYOKU_RESOURCE_BLOCK =
        BLOCKS.register(
            CreativeYoryokuResourceBlock.BLOCK_ID,
            () -> new CreativeYoryokuResourceBlock(
                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE)
            )
        );
}
