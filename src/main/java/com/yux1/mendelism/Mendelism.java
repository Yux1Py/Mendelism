package com.yux1.mendelism;

import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.item.ModItems;
import com.yux1.mendelism.util.ModModelPredicateProvider;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mendelism implements ModInitializer {

	public static final String MOD_ID = "mendelism";

	public static final Logger LOGGER = LoggerFactory.getLogger(Mendelism.MOD_ID);

	@Override
	public void onInitialize() {

		//放在最顶上
		//ModConfigureFeatures.registerConfigureFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModModelPredicateProvider.registerModModels();
		//ModRegistries.registerModStuffs();

		//ModWorldGeneration.generateModWorldGen();

		LOGGER.info("Mendelism Start!");
	}
}
