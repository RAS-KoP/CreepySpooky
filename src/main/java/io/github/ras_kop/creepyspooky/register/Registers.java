package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.Config;
import io.github.ras_kop.creepyspooky.CreepySpooky;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

public class Registers {

    public static void register(CreepySpooky target, IEventBus modEventBus, ModContainer modContainer){
        // Register the commonSetup method for modloading
        modEventBus.addListener(target::commonSetup);

        // Register the Deferred Register to the mod event bus so tabs get registered
        CreativeTabRegister.register(modEventBus);

        AttributesRegister.register(modEventBus);
        DataComponentRegister.register(modEventBus);

        BlockRegister.register(modEventBus);
        ItemRegister.register(modEventBus);
        BlockEntityRegister.register(modEventBus);

        EntityRegister.register(modEventBus);
        modEventBus.addListener(EntityRegister::registerAttributes);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (CreepySpooky) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(target);
        
        if(FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(EntityModelRegister::register);
            modEventBus.addListener(BlockModelRegister::register);
        }

        // Register the item to a creative tab
        modEventBus.addListener(target::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
