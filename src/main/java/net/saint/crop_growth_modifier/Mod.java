package net.saint.crop_growth_modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import net.saint.crop_growth_modifier.config.ModConfig;
import net.saint.crop_growth_modifier.util.ConfigBlockManager;

public class Mod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("crop_growth_modifier");

	public static ModConfig CONFIG;

	public static ConfigBlockManager CONFIG_BLOCK_MANAGER;

	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
		var configHolder = AutoConfig.getConfigHolder(ModConfig.class);

		CONFIG = configHolder.getConfig();
		CONFIG_BLOCK_MANAGER = new ConfigBlockManager();

		configHolder.registerSaveListener((holder, config) -> {
			reload();
			return ActionResult.PASS;
		});
	}

	public void reload() {
		CONFIG_BLOCK_MANAGER.reload();
	}
}
