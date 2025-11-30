package net.saint.crop_growth_modifier.mixinlogic;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public interface CropBlockMixinLogic extends GrowableBlockMixinLogic {

	public default void cgm$$randomTick(CropBlock block, BlockState state, ServerWorld world, BlockPos position, Random random,
			CallbackInfo callbackInfo) {
		if (!shouldAllowRandomTickForCrop(block, state, world, position, random)) {
			callbackInfo.cancel();
		}
	}

	public default void cgm$$applyGrowth(World world, CropBlock block, BlockPos position, BlockState state, CallbackInfo callbackInfo) {
		if (!shouldApplyGrowth(world, position, state)) {
			callbackInfo.cancel();
			return;
		}
	}

}
