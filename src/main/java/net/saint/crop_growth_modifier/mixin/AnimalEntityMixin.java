package net.saint.crop_growth_modifier.mixin;

import java.util.function.Consumer;
import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import blue.endless.jankson.annotation.Nullable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.saint.crop_growth_modifier.library.CowEntityMilkAccessor;
import net.saint.crop_growth_modifier.mixinlogic.AnimalEntityMixinLogic;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin implements AnimalEntityMixinLogic {

	// Properties

	public float cgm$getMilkAmount() {
		return returnWithCowEntityMilkAccess(cowEntity -> {
			return cowEntity.cgm$getMilkAmount();
		}, 0f);
	}

	public void cgm$setMilkAmount(float milkAmount) {
		withCowEntityMilkAccess(cowEntity -> {
			cowEntity.cgm$setMilkAmount(milkAmount);
		});
	}

	public long cgm$getLastMilkProductionTime() {
		return returnWithCowEntityMilkAccess(cowEntity -> {
			return cowEntity.cgm$getLastMilkProductionTime();
		}, 0L);
	}

	public void cgm$setLastMilkProductionTime(long lastMilkProductionTime) {
		withCowEntityMilkAccess(cowEntity -> {
			cowEntity.cgm$setLastMilkProductionTime(lastMilkProductionTime);
		});
	}

	// Init

	@Inject(method = "<init>", at = @At("TAIL"))
	public void cgm$init(CallbackInfo callbackInfo) {
		cgm$initClientNetworking();
	}

	// NBT

	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	public void cgm$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo callbackInfo) {
		withCowEntity(cowEntity -> {
			cgm$writeNbt(cowEntity, nbt);
		});
	}

	@Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
	public void cgm$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo callbackInfo) {
		withCowEntity(cowEntity -> {
			cgm$readNbt(cowEntity, nbt);
		});
	}

	// Tick

	@Inject(method = "mobTick", at = @At("TAIL"))
	private void cgm$mobTick(CallbackInfo callbackInfo) {
		withCowEntity(cowEntity -> {
			cgm$mobTick(cowEntity);
		});
	}

	// Breeding

	@Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;Lnet/minecraft/entity/passive/PassiveEntity;)V", at = @At("HEAD"))
	private void cgm$breed(ServerWorld world, AnimalEntity other, @Nullable PassiveEntity baby, CallbackInfo callbackInfo) {
		cgm$breedWithBaby(world, other, baby);
	}

	// Convenience Access

	private void withCowEntity(Consumer<CowEntity> block) {
		if (!((Object) this instanceof CowEntity)) {
			return;
		}

		var cowEntity = (CowEntity) (Object) this;
		block.accept(cowEntity);
	}

	private void withCowEntityMilkAccess(Consumer<CowEntityMilkAccessor> block) {
		if (!((Object) this instanceof CowEntity)) {
			return;
		}

		var cowEntity = (CowEntityMilkAccessor) (Object) this;
		block.accept(cowEntity);
	}

	private <ReturnType> ReturnType returnWithCowEntityMilkAccess(Function<CowEntityMilkAccessor, ReturnType> block,
			ReturnType fallbackValue) {
		if (!((Object) this instanceof CowEntity)) {
			return fallbackValue;
		}

		var cowEntity = (CowEntityMilkAccessor) (Object) this;
		return block.apply(cowEntity);
	}

}
