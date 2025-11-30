package net.saint.crop_growth_modifier.mixinlogic;

import static net.minecraft.util.math.MathHelper.clamp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.saint.crop_growth_modifier.Mod;

public interface GrowableBlockMixinLogic {

	// Ticking

	public default boolean shouldAllowRandomTickForCrop(Block block, BlockState state, ServerWorld world, BlockPos pos, Random random) {
		return shouldAllowRandomTick(random, Mod.CONFIG.cropTickChance);
	}

	public default boolean shouldAllowRandomTickForStem(Block block, BlockState state, ServerWorld world, BlockPos pos, Random random) {
		return shouldAllowRandomTick(random, Mod.CONFIG.stemTickChance);
	}

	public default boolean shouldAllowRandomTickForMisc(Block block, BlockState state, ServerWorld world, BlockPos pos, Random random) {
		return shouldAllowRandomTick(random, Mod.CONFIG.miscTickChance);
	}

	public default boolean shouldAllowRandomTickForSapling(Block block, BlockState state, ServerWorld world, BlockPos pos, Random random) {
		return shouldAllowRandomTick(random, Mod.CONFIG.saplingTickChance);
	}

	private boolean shouldAllowRandomTick(Random random, float chance) {
		var randomValue = random.nextFloat();

		if (randomValue > chance) {
			return false;
		}

		return true;
	}

	// Growth

	public default boolean shouldApplyGrowth(World world, BlockPos pos, BlockState state) {
		return world.random.nextFloat() < Mod.CONFIG.cropTickChance;
	}

	public default int getGrowthAmountForAllowedEvent(World world, int maxAge) {
		var growthMin = clamp(Mod.CONFIG.cropGrowthStagesMin, 1, maxAge);
		var growthMax = clamp(Mod.CONFIG.cropGrowthStagesMax, growthMin + 1, maxAge + 1);

		return world.random.nextBetween(growthMin, growthMax);
	}

}
