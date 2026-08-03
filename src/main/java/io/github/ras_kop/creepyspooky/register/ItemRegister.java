package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.CreepySpooky;
import io.github.ras_kop.creepyspooky.item.YorikiLinkingWandItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreepySpooky.MODID);

    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
        "example_block",
        BlockRegister.EXAMPLE_BLOCK
    );
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem(
        "example_item",
        new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(1)
            .saturationModifier(2F)
            .build())
    );

    public static final DeferredItem<BlockItem> CREATIVE_YORIKI_POOL = ITEMS.registerSimpleBlockItem(
        "creative_yoriki_pool",
        BlockRegister.CREATIVE_YORIKI_POOL
    );
    public static final DeferredItem<BlockItem> YORIKI_TRANSMITTER = ITEMS.registerSimpleBlockItem(
        "yoriki_transmitter",
        BlockRegister.YORIKI_TRANSMITTER
    );
    public static final DeferredItem<BlockItem> YORIKI_RECEIVER = ITEMS.registerSimpleBlockItem(
        "yoriki_receiver",
        BlockRegister.YORIKI_RECEIVER
    );
    public static final DeferredItem<BlockItem> YORIKI_FURNACE = ITEMS.registerSimpleBlockItem(
        "yoriki_furnace",
        BlockRegister.YORIKI_FURNACE
    );
    public static final DeferredItem<BlockItem> YORIKI_CABLE = ITEMS.registerSimpleBlockItem(
        "yoriki_cable",
        BlockRegister.YORIKI_CABLE
    );
    public static final DeferredItem<YorikiLinkingWandItem> YORIKI_LINKING_WAND = ITEMS.register(
        "yoriki_linking_wand",
        () -> new YorikiLinkingWandItem(new Item.Properties().stacksTo(1))
    );

    private ItemRegister() {
    }
}
