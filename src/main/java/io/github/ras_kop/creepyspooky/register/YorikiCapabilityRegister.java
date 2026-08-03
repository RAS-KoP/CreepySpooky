package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.yoriki.block.entity.CreativeYorikiPoolBlockEntity;
import io.github.ras_kop.creepyspooky.yoriki.block.entity.YorikiEnergyBlockEntity;
import io.github.ras_kop.creepyspooky.yoriki.block.entity.YorikiFurnaceBlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public final class YorikiCapabilityRegister {
    private YorikiCapabilityRegister() {
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            YorikiBlockEntityRegister.CREATIVE_YORIKI_POOL.get(),
            CreativeYorikiPoolBlockEntity::getEnergyStorage
        );
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            YorikiBlockEntityRegister.YORIKI_TRANSMITTER.get(),
            YorikiEnergyBlockEntity::getEnergyStorage
        );
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            YorikiBlockEntityRegister.YORIKI_RECEIVER.get(),
            YorikiEnergyBlockEntity::getEnergyStorage
        );
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            YorikiBlockEntityRegister.YORIKI_FURNACE.get(),
            YorikiFurnaceBlockEntity::getEnergyStorage
        );
    }
}
