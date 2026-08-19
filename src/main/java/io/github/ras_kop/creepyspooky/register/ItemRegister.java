package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegister {

    public static final DeferredRegister.Items ITEMS =
        DeferredRegister.createItems(CreepySpooky.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = 
        ITEMS.registerSimpleBlockItem("example_block", BlockRegister.EXAMPLE_BLOCK);

    public static final DeferredItem<Item> EXAMPLE_ITEM = 
        ITEMS.registerSimpleItem(
            "example_item", 
            new Item.Properties().food(
                new FoodProperties
                    .Builder()
                    .alwaysEdible()
                    .nutrition(1)
                    .saturationModifier(2f)
                    .build()
            )
        );
    
    //かまどブロックアイテムの登録
    public static final DeferredItem<BlockItem> CREEPY_FURNACE_ITEM =
        ITEMS.register(
                "creepy_furnace",
                () -> new BlockItem(
                        BlockRegister.CREEPY_FURNACE.get(),
                        new Item.Properties()
                )
        );

}
