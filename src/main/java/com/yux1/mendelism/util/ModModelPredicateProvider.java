package com.yux1.mendelism.util;

import com.yux1.mendelism.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelPredicateProvider {

    private static void registerPea(Item pea){
        FabricModelPredicateProviderRegistry.register(pea, new Identifier("seed_color"),
                ((stack, world, entity, seed) -> {
                    if (stack.getNbt() != null) {
                        return (entity != null) &&
                                (stack.getNbt().getString("pea.seed_color") == "green") ? 1.0f : 0.0f;
                    }
                    return 0;
                }));
    }

    public static void registerModModels(){
        registerPea(ModItems.PEA);
    }
}
