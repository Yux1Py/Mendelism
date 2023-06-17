package com.yux1.mendelism.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlaceFeatures {
    public static final RegistryEntry<PlacedFeature> PEA_CROP_PLANT_PLACED =
            PlacedFeatures.register("pea_crop_plant_placed",
                    ModConfigureFeatures.PEA_CROP_PLANT, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEA_CROP_PLANT_WHITE_FLOWER_PLACED =
            PlacedFeatures.register("pea_crop_plant_white_flower_placed",
                    ModConfigureFeatures.PEA_CROP_PLANT_WHITE_FLOWER, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEA_CROP_PLANT_SHRUNKEN_PEEL_PLACED =
            PlacedFeatures.register("pea_crop_shrunken_peel_placed",
                    ModConfigureFeatures.PEA_CROP_PLANT_SHRUNKEN_PEEL, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEA_CROP_PLANT_YELLOW_PEEL_PLACED =
            PlacedFeatures.register("pea_crop_plant_yellow_peel_placed",
                    ModConfigureFeatures.PEA_CROP_PLANT_YELLOW_PEEL, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEA_CROP_PLANT_WRINKLED_SEED_PLACED =
            PlacedFeatures.register("pea_crop_plant_wrinkled_seed_placed",
                    ModConfigureFeatures.PEA_CROP_PLANT_WRINKLED_SEED, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PEA_CROP_PLANT_YELLOW_SEED_PLACED =
            PlacedFeatures.register("pea_crop_plant_yellow_seed_placed",
                    ModConfigureFeatures.PEA_CROP_PLANT_YELLOW_SEED, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

}
