package com.yux1.mendelism.world.feature.gen;

import com.yux1.mendelism.world.feature.ModPlaceFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

public class ModFlowerGeneration {
    public static void generateFlowers(){
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlaceFeatures.PEA_CROP_PLANT_PLACED.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.RIVER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlaceFeatures.PEA_CROP_PLANT_WHITE_FLOWER_PLACED.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SAVANNA),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlaceFeatures.PEA_CROP_PLANT_SHRUNKEN_PEEL_PLACED.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlaceFeatures.PEA_CROP_PLANT_YELLOW_PEEL_PLACED.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.JUNGLE),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlaceFeatures.PEA_CROP_PLANT_WRINKLED_SEED_PLACED.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.TAIGA),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlaceFeatures.PEA_CROP_PLANT_YELLOW_SEED_PLACED.getKey().get());
    }
}
