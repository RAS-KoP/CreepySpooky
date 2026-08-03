package io.github.ras_kop.creepyspooky.yoriki;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public final class YorikiTransfer {
    private YorikiTransfer() {
    }

    public static int pushEnergy(Level level, BlockPos sourcePos, IEnergyStorage source, Direction... directions) {
        // One source block is limited to 64 Yoriki per tick across all outputs.
        int remaining = YorikiConstants.NETWORK_TRANSFER_RATE;

        for (Direction direction : directions) {
            if (remaining <= 0) {
                break;
            }

            IEnergyStorage target = level.getCapability(
                Capabilities.EnergyStorage.BLOCK,
                sourcePos.relative(direction),
                direction.getOpposite()
            );
            if (target == null || !target.canReceive()) {
                continue;
            }

            int offered = source.extractEnergy(remaining, true);
            if (offered <= 0) {
                break;
            }

            int received = target.receiveEnergy(offered, false);
            if (received > 0) {
                source.extractEnergy(received, false);
                remaining -= received;
            }
        }

        return YorikiConstants.NETWORK_TRANSFER_RATE - remaining;
    }
}
