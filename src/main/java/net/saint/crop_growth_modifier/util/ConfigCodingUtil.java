package net.saint.crop_growth_modifier.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.util.Identifier;

public final class ConfigCodingUtil {

	public static Set<Identifier> decodeBlockIdentifiers(String encodedIdentifiers) {
		var rawIdentifiers = List.of(encodedIdentifiers.split(",")).stream().map(value -> {
			return value.trim();
		}).filter(value -> {
			return !value.isEmpty();
		}).toList();

		var identifiers = rawIdentifiers.stream().map(value -> {
			return Identifier.tryParse(value);
		}).filter(identifier -> {
			return identifier != null;
		}).collect(Collectors.toSet());

		return identifiers;
	}

}
