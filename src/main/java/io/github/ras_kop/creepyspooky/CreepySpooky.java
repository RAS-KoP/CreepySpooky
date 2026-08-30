package io.github.ras_kop.creepyspooky;


import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import io.github.ras_kop.creepyspooky.register.AttributesRegister;
import io.github.ras_kop.creepyspooky.register.BlockEntityRegister;
import io.github.ras_kop.creepyspooky.register.BlockModelRegister;
import io.github.ras_kop.creepyspooky.register.BlockRegister;
import io.github.ras_kop.creepyspooky.register.CreativeTabRegister;
import io.github.ras_kop.creepyspooky.register.DateComponentRegister;
import io.github.ras_kop.creepyspooky.register.EntityModelRegister;
import io.github.ras_kop.creepyspooky.register.EntityRegister;
import io.github.ras_kop.creepyspooky.register.ItemRegister;
import io.github.ras_kop.creepyspooky.register.Registers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreepySpooky.MODID)
public class CreepySpooky {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "creepyspooky";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CreepySpooky(IEventBus modEventBus, ModContainer modContainer) {
        Registers.register(this, modEventBus, modContainer);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ItemRegister.EXAMPLE_BLOCK_ITEM.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
