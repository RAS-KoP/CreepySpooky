package io.github.ras_kop.creepyspooky.register;

import io.github.ras_kop.creepyspooky.block.entity.CreativeYorikiPoolBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.YorikiCableBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.YorikiFurnaceBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.YorikiReceiverBlockEntity;
import io.github.ras_kop.creepyspooky.block.entity.YorikiTransmitterBlockEntity;
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
            YorikiTransmitterBlockEntity::getEnergyStorage
        );
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            YorikiBlockEntityRegister.YORIKI_RECEIVER.get(),
            YorikiReceiverBlockEntity::getEnergyStorage
        );
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            YorikiBlockEntityRegister.YORIKI_FURNACE.get(),
            YorikiFurnaceBlockEntity::getEnergyStorage
        );
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            YorikiBlockEntityRegister.YORIKI_CABLE.get(),
            YorikiCableBlockEntity::getEnergyStorage
        );
    }
}
