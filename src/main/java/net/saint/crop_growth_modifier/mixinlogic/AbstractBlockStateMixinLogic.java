package net.saint.crop_growth_modifier.mixinlogic;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.saint.crop_growth_modifier.Mod;

public interface AbstractBlockStateMixinLogic extends GrowableBlockMixinLogic {

	public boolean enableLogging = false;

	public default void commercialize$randomTick(ServerWorld world, BlockState blockState, BlockPos position, Random random,
			CallbackInfo callbackInfo) {
		var block = blockState.getBlock();
		var blockId = Registries.BLOCK.getId(block);

		if (isCropBlock(block, blockId)) {
			if (!shouldAllowRandomTickForCrop(block, blockState, world, position, random)) {
				if (enableLogging) {
					Mod.LOGGER.info("Canceled random crop block tick for '{}' at {}.", blockId, position);
				}

				callbackInfo.cancel();
			}

			return;
		}

		if (isStemBlock(block, blockId)) {
			if (!shouldAllowRandomTickForStem(block, blockState, world, position, random)) {
				if (enableLogging) {
					Mod.LOGGER.info("Canceled random stem block tick for '{}' at {}.", blockId, position);
				}

				callbackInfo.cancel();
			}

			return;
		}

		if (isMiscBlock(block, blockId)) {
			if (!shouldAllowRandomTickForMisc(block, blockState, world, position, random)) {
				if (enableLogging) {
					Mod.LOGGER.info("Canceled random misc block tick for '{}' at {}.", blockId, position);
				}

				callbackInfo.cancel();
			}

			return;
		}

		if (isSaplingBlock(block, blockId)) {
			if (!shouldAllowRandomTickForSapling(block, blockState, world, position, random)) {
				if (enableLogging) {
					Mod.LOGGER.info("Canceled random sapling block tick for '{}' at {}.", blockId, position);
				}

				callbackInfo.cancel();
			}

			return;
		}
	}

	// Classification

	private boolean isCropBlock(Block block, Identifier blockId) {
		if (Mod.CONFIG.affectAllCropBlocks && (block instanceof CropBlock || block instanceof PlantBlock)) {
			return true;
		}

		return Mod.CONFIG_BLOCK_MANAGER.getCropBlockIdentifiers().contains(blockId);
	}

	private boolean isStemBlock(Block block, Identifier blockId) {
		if (Mod.CONFIG.affectAllStemBlocks && block instanceof StemBlock) {
			return true;
		}

		return Mod.CONFIG_BLOCK_MANAGER.getStemBlockIdentifiers().contains(blockId);
	}

	private boolean isMiscBlock(Block block, Identifier blockId) {
		return Mod.CONFIG_BLOCK_MANAGER.getMiscBlockIdentifiers().contains(blockId);
	}

	private boolean isSaplingBlock(Block block, Identifier blockId) {
		if (block instanceof SaplingBlock) {
			return true;
		}

		return false;
	}

}
