package com.yux1.mendelism.util;

import com.yux1.mendelism.Mendelism;
import com.yux1.mendelism.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.block.LightBlock;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ModModelPredicateProvider {

    /*private static void registerPea(){
        ModelPredicateProviderRegistry.register(ModItems.PEA, new Identifier("seed_color"),
                ((stack, world, entity, seed) -> {
                    if (stack.getNbt() != null) {
                        return ((entity != null) &&
                                (Objects.equals(stack.getNbt().getString("pea.seed_color.1"), "green")) ? 1.0f : 0.0f);
                    }
                    return 1.0f;
                }));
    }*/

    private static void registerPeaPod(Item peaPod){
        ModelPredicateProviderRegistry.register(peaPod, new Identifier(Mendelism.MOD_ID, "peel_color"),
                (stack, world, entity, seed) -> {
                    return 1.0f;
                });
    }

    public static void registerModModels(){
        //registerPea();
        registerPeaPod(ModItems.PEA_POD);
    }
}
