package com.yux1.mendelism;

import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.partical.ModParticles;
import com.yux1.mendelism.partical.custom.PollenParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;

public class MendelismClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP_PLANT_WHITE_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP_PLANT_SHRUNKEN_PEEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP_PLANT_YELLOW_PEEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP_PLANT_WRINKLED_SEED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP_PLANT_YELLOW_SEED, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(ModParticles.RED_POLLEN_PARTICLE, PollenParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.YELLOW_POLLEN_PARTICLE, PollenParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PINK_POLLEN_PARTICLE, PollenParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.WHITE_POLLEN_PARTICLE, PollenParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.FEATHER_PARTICLE, PollenParticle.Factory::new);
    }
}
