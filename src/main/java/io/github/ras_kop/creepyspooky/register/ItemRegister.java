package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.item.YoryokuWand;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegister {

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(Registries.ITEM, CreepySpooky.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    public static final DeferredHolder<Item, BlockItem> EXAMPLE_BLOCK_ITEM = 
        ITEMS.register(
            "example_block", 
            () -> new BlockItem(
                BlockRegister.EXAMPLE_BLOCK.get(), 
                new Item.Properties()
            )
        );

    public static final DeferredHolder<Item, Item> EXAMPLE_ITEM = 
        ITEMS.register(
            "example_item", 
            () -> new Item(
                new Item.Properties().food(
                    new FoodProperties
                        .Builder()
                        .alwaysEdible()
                        .nutrition(1)
                        .saturationModifier(2f)
                        .build()
                )
            )
        );

    public static final DeferredHolder<Item, YoryokuWand> YORYOKU_WAND =
        ITEMS.register(
        "yoryoku_wand",
        () -> new YoryokuWand(
            new Item.Properties()
        )
    );    
}
