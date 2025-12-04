package net.saint.crop_growth_modifier.util;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.util.Identifier;
import net.saint.crop_growth_modifier.Mod;

public class ConfigBlockManager {

	// Properties

	private Set<Identifier> cropBlockIdentifiers = new HashSet<>();
	private Set<Identifier> stemBlockIdentifiers = new HashSet<>();
	private Set<Identifier> miscBlockIdentifiers = new HashSet<>();

	// Init

	public ConfigBlockManager() {
		reload();
	}

	// Reload

	public void reload() {
		this.cropBlockIdentifiers = ConfigCodingUtil.decodeBlockIdentifiers(Mod.CONFIG.cropBlockIdentifiers);
		this.stemBlockIdentifiers = ConfigCodingUtil.decodeBlockIdentifiers(Mod.CONFIG.stemBlockIdentifiers);
		this.miscBlockIdentifiers = ConfigCodingUtil.decodeBlockIdentifiers(Mod.CONFIG.miscBlockIdentifiers);
	}

	// Access

	public Set<Identifier> getCropBlockIdentifiers() {
		return cropBlockIdentifiers;
	}

	public Set<Identifier> getStemBlockIdentifiers() {
		return stemBlockIdentifiers;
	}

	public Set<Identifier> getMiscBlockIdentifiers() {
		return miscBlockIdentifiers;
	}

}
