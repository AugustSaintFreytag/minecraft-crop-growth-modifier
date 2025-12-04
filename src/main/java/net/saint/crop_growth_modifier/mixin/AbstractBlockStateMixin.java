package net.saint.crop_growth_modifier.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.saint.crop_growth_modifier.mixinlogic.AbstractBlockStateMixinLogic;

@Mixin(AbstractBlockState.class)
public class AbstractBlockStateMixin implements AbstractBlockStateMixinLogic {

	@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
	private void cgm$mixin$randomTick(ServerWorld world, BlockPos position, Random random, CallbackInfo callbackInfo) {
		var blockState = (BlockState) (Object) this;
		commercialize$randomTick(world, blockState, position, random, callbackInfo);
	}

}
