package net.saint.crop_growth_modifier.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "crop_growth_modifier")
public class ModConfig implements ConfigData {
	// Crop Blocks

	@Comment("Chance for a random world tick to be forwarded to a crop block (e.g. wheat, carrots). Default: 1.0 (100%)")
	public float cropTickChance = 1.0f;

	@Comment("Minimum number of stages a crop block grows when growth happens, default is vanilla. Default: 2")
	public int cropGrowthStagesMin = 2;

	@Comment("Maximum number of stages a crop block grows when growth happens, default is vanilla. Default: 5")
	public int cropGrowthStagesMax = 5;

	@Comment("Enables affecting all crop block types by default. Can be disabled to only use blocks defined manually below. Default: true")
	public boolean affectAllCropBlocks = true;

	@Comment("Comma-separated set of block ids for crop blocks to be affected. (Example: 'minecraft:wheat, minecraft:beetroot')")
	public String cropBlockIdentifiers = "";

	// Stem Blocks

	@Comment("Chance for a random world tick to be forwarded to a stem block (e.g. pumpkins, melons). Default: 1.0 (100%)")
	public float stemBlockChance = 1.0f;

	@Comment("Enables affecting all stem block types by default. Can be disabled to only use blocks defined manually below. Default: true")
	public boolean affectAllStemBlocks = true;

	@Comment("Comma-separated set of block ids for stem blocks to be affected. (Example: 'minecraft:melon_stem, minecraft:pumpkin_stem')")
	public String stemBlockIdentifiers = "";

	// Misc Blocks

	@Comment("Chance for a random world tick to be forwarded to other growable blocks (e.g. sugar cane, cactus). Default: 1.0 (100%)")
	public float miscBlockChance = 1.0f;

	@Comment("Comma-separated set of block ids for other growable blocks to be affected. (Example: 'minecraft:sugar_cane, minecraft:cactus')")
	public String miscBlockIdentifiers = "minecraft:sugar_cane, minecraft:cactus, minecraft:bamboo, minecraft:cocoa";

	// Saplings

	@Comment("Chance for a random world tick to be forwarded to sapling blocks. Default: 1.0 (100%)")
	public float saplingBlockChance = 1.0f;

	// Animals

	@Comment("Cooldown for animal breeding (vanilla default: 6k ticks = 5 minutes). Default: 6000")
	public int animalBreedingCooldown = 6000;

	@Comment("Rolled extra multiplier for applied cooldown for animal breeding (1.0 = x1, 2.0 = between 1x and 2x is applied, lower base cooldown for wider range). Default: 0")
	public float animalBreedingCooldownMultiplier = 1f;

	@Comment("Enable limited milk production for cows. Default: false")
	public boolean cowLimitedMilkProduction = false;

	@Comment("Rate for cows to produce buckets of milk per 100 ticks (5 seconds). 1.0f = 1 bucket. Cows may hold more than one bucket. Formula for time calculation in minutes: (1 / <rate value> * (100/20)) / 60. Default: 0.00825 (approx. 10 minutes)")
	public float cowMilkProductionPerHundredTicks = 0.00825f;

	@Comment("Maximum amount of milk a cow can hold in buckets. Default: 2.0 buckets")
	public float cowMilkProductionCapacity = 2.0f;

	@Comment("Maximum amount of milk a cow can initially get randomly set to. Default: 0.5 (50%)")
	public float cowMilkInitialRandomFraction = 0.5f;
}