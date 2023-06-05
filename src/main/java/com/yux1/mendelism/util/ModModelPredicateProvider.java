package com.yux1.mendelism.util;

import com.yux1.mendelism.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class ModModelPredicateProvider {

    private static void registerPea(){
        ModelPredicateProviderRegistry.register(ModItems.PEA, new Identifier("seed_color"),
                ((stack, world, entity, seed) -> {
                    if (stack.getNbt() != null) {
                        return ((entity != null) &&
                                (Objects.equals(stack.getNbt().getString("pea.seed_color.1"), "green")) ? 1.0f : 0.0f);
                    }
                    return 1.0f;
                }));
    }

    /*public static void registerModModels(){
        registerPea();
    }*/
}
