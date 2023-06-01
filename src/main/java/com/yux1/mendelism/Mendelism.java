package com.yux1.mendelism;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mendelism implements ModInitializer {

	public static final String MOD_ID = "mendelism";

	public static final Logger LOGGER = LoggerFactory.getLogger(Mendelism.MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}
