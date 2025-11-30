package net.saint.crop_growth_modifier.library;

public interface CowEntityMilkAccessor {

	public float cgm$getMilkAmount();

	public void cgm$setMilkAmount(float milkProductionAmount);

	public long cgm$getLastMilkProductionTime();

	public void cgm$setLastMilkProductionTime(long lastMilkProductionTime);

}
