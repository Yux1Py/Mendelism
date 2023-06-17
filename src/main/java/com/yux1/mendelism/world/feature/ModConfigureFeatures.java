package com.yux1.mendelism.world.feature;

import com.yux1.mendelism.Mendelism;
import com.yux1.mendelism.block.ModBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfigureFeatures {

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEA_CROP_PLANT =
            ConfiguredFeatures.register("pea_crop_plant", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(1,
                            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PEA_CROP_PLANT)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEA_CROP_PLANT_WHITE_FLOWER =
            ConfiguredFeatures.register("pea_crop_plant_white_flower", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(1,
                            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PEA_CROP_PLANT_WHITE_FLOWER)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEA_CROP_PLANT_SHRUNKEN_PEEL =
            ConfiguredFeatures.register("pea_crop_plant_shrunken_peel", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(1,
                            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PEA_CROP_PLANT_SHRUNKEN_PEEL)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEA_CROP_PLANT_YELLOW_PEEL =
            ConfiguredFeatures.register("pea_crop_plant_yellow_peel", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(1,
                            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PEA_CROP_PLANT_YELLOW_PEEL)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEA_CROP_PLANT_WRINKLED_SEED =
            ConfiguredFeatures.register("pea_crop_plant_wrinkled_seed", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(1,
                            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PEA_CROP_PLANT_WRINKLED_SEED)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEA_CROP_PLANT_YELLOW_SEED =
            ConfiguredFeatures.register("pea_crop_plant_yellow_seed", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(1,
                            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PEA_CROP_PLANT_YELLOW_SEED)))));


    public static void registerConfigureFeatures(){
        System.out.println("Registering ModConfigureFeatures for " + Mendelism.MOD_ID);
    }
}
