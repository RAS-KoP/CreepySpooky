package io.github.ras_kop.creepyspooky.block.entity;

import javax.annotation.Nullable;

import io.github.ras_kop.creepyspooky.register.YorikiBlockEntityRegister;
import io.github.ras_kop.creepyspooky.yoriki.YorikiConstants;
import io.github.ras_kop.creepyspooky.yoriki.YorikiEnergyStorage;
import io.github.ras_kop.creepyspooky.yoriki.YorikiSidedStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.neoforged.neoforge.energy.IEnergyStorage;

public final class YorikiFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    private static final int DEFAULT_COOKING_TIME = 200;

    private final YorikiEnergyStorage energyStorage;
    private final IEnergyStorage inputStorage;
    private final RecipeManager.CachedCheck<SingleRecipeInput, SmeltingRecipe> quickCheck;
    private final ContainerData yorikiData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> energyStorage.getEnergyStored();
                case 1 -> energyStorage.getMaxEnergyStored();
                case 2 -> cookingProgress;
                case 3 -> cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> energyStorage.setEnergy(value);
                case 1 -> cookingTotalTime = Math.max(1, value);
                case 2 -> cookingProgress = value;
                case 3 -> cookingTotalTime = Math.max(1, value);
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
    private int cookingProgress;
    private int cookingTotalTime = DEFAULT_COOKING_TIME;

    public YorikiFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(YorikiBlockEntityRegister.YORIKI_FURNACE.get(), pos, state, RecipeType.SMELTING);
        this.energyStorage = new YorikiEnergyStorage(
            YorikiConstants.NETWORK_CAPACITY,
            YorikiConstants.FURNACE_CONSUMPTION_RATE,
            YorikiConstants.FURNACE_CONSUMPTION_RATE,
            ignored -> setChanged()
        );
        this.inputStorage = new YorikiSidedStorage(energyStorage, true, false);
        this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
    }

    @Nullable
    public IEnergyStorage getEnergyStorage(@Nullable net.minecraft.core.Direction side) {
        return inputStorage;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("YorikiEnergy", Tag.TAG_INT)) {
            energyStorage.setEnergy(tag.getInt("YorikiEnergy"));
        }
        cookingProgress = tag.getInt("YorikiProgress");
        cookingTotalTime = Math.max(1, tag.getInt("YorikiTotalTime"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("YorikiEnergy", energyStorage.getEnergyStored());
        tag.putInt("YorikiProgress", cookingProgress);
        tag.putInt("YorikiTotalTime", cookingTotalTime);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.creepyspooky.yoriki_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new FurnaceMenu(id, inventory, this, yorikiData);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        super.setItem(index, stack);
        if (index == 0) {
            cookingProgress = 0;
            cookingTotalTime = DEFAULT_COOKING_TIME;
        }
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return index == 0;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, YorikiFurnaceBlockEntity blockEntity) {
        boolean wasLit = state.getValue(AbstractFurnaceBlock.LIT);
        boolean changed = false;
        ItemStack input = blockEntity.getItem(0);
        RecipeHolder<? extends AbstractCookingRecipe> recipe = blockEntity.quickCheck
            .getRecipeFor(new SingleRecipeInput(input), level)
            .orElse(null);

        if (recipe == null || !canBurn(level, recipe, blockEntity)) {
            if (blockEntity.cookingProgress != 0) {
                blockEntity.cookingProgress = 0;
                changed = true;
            }
        } else if (blockEntity.energyStorage.extractEnergy(YorikiConstants.FURNACE_CONSUMPTION_RATE, false)
            == YorikiConstants.FURNACE_CONSUMPTION_RATE) {
            if (blockEntity.cookingProgress == 0) {
                blockEntity.cookingTotalTime = Math.max(1, recipe.value().getCookingTime());
            }
            blockEntity.cookingProgress++;
            changed = true;

            if (blockEntity.cookingProgress >= blockEntity.cookingTotalTime) {
                blockEntity.cookingProgress = 0;
                smelt(level, recipe, blockEntity);
            }
        }

        boolean isLit = blockEntity.cookingProgress > 0;
        if (wasLit != isLit) {
            level.setBlock(pos, state.setValue(AbstractFurnaceBlock.LIT, isLit), 3);
            changed = true;
        }
        if (changed) {
            blockEntity.setChanged();
        }
    }

    private static boolean canBurn(Level level, RecipeHolder<? extends AbstractCookingRecipe> recipe, Container furnace) {
        ItemStack result = recipe.value().assemble(new SingleRecipeInput(furnace.getItem(0)), level.registryAccess());
        if (result.isEmpty()) {
            return false;
        }

        ItemStack output = furnace.getItem(2);
        if (output.isEmpty()) {
            return true;
        }
        return ItemStack.isSameItemSameComponents(output, result)
            && output.getCount() + result.getCount() <= Math.min(furnace.getMaxStackSize(), output.getMaxStackSize());
    }

    private static void smelt(Level level, RecipeHolder<? extends AbstractCookingRecipe> recipe, YorikiFurnaceBlockEntity furnace) {
        ItemStack result = recipe.value().assemble(new SingleRecipeInput(furnace.getItem(0)), level.registryAccess());
        ItemStack output = furnace.getItem(2);
        if (output.isEmpty()) {
            furnace.setItem(2, result.copy());
        } else if (ItemStack.isSameItemSameComponents(output, result)) {
            output.grow(result.getCount());
        }
        furnace.getItem(0).shrink(1);
        furnace.setRecipeUsed(recipe);
    }
}
