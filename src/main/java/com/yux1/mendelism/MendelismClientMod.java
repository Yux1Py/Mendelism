package com.yux1.mendelism;

import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class MendelismClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicateProvider.registerModModels();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEA_CROP, RenderLayer.getCutout());
    }
}
