package net.saint.crop_growth_modifier.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.saint.crop_growth_modifier.mixinlogic.CropBlockMixinLogic;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin implements CropBlockMixinLogic {

	// Properties

	@Shadow
	public abstract int getMaxAge();

	// Logic

	@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
	private void cgm$mixin$randomTick(BlockState state, ServerWorld world, BlockPos position, Random random, CallbackInfo callbackInfo) {
		var block = (CropBlock) (Object) this;
		cgm$randomTick(block, state, world, position, random, callbackInfo);
	}

	@Inject(method = "applyGrowth", at = @At("HEAD"), cancellable = true)
	private void cgm$mixin$applyGrowth(World world, BlockPos position, BlockState state, CallbackInfo callbackInfo) {
		var block = (CropBlock) (Object) this;
		cgm$applyGrowth(world, block, position, state, callbackInfo);
	}

	@Inject(method = "getGrowthAmount", at = @At("HEAD"), cancellable = true)
	private void cgm$mixin$getGrowthAmount(World world, CallbackInfoReturnable<Integer> callbackInfo) {
		var growthAmount = getGrowthAmountForAllowedEvent(world, getMaxAge());
		callbackInfo.setReturnValue(growthAmount);
	}

}
