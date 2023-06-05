package com.yux1.mendelism.util;

import com.yux1.mendelism.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

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
        ModelPredicateProviderRegistry.register(peaPod, new Identifier("peel_color"),
                ((stack, world, entity, seed) -> {
                    return checkNbt(stack, world, entity, seed, "peel_color") ? 1.0f : 0.0f;
                }));
    }

    public static void registerModModels(){
        //registerPea();
        registerPeaPod(ModItems.PEA_POD);
    }

    public static boolean checkNbt(ItemStack stack, World world, LivingEntity entity, int seed, String nbtKey){
        /*if (stack.hasNbt()){
            if (entity != null){
                assert stack.getNbt() != null;
                int nbtInt =  stack.getNbt().getInt(nbtKey);
                if (nbtInt == 3){
                    return false;
                }
            }
        }
        return true;*/
        if (stack.getItem() == ModItems.PEA_POD){
            return true;
        }
        return false;
    }

    public static boolean checkNbt(ItemStack stack, World world, LivingEntity entity, String nbtKey) {
        if (stack.hasNbt()){
            assert stack.getNbt() != null;
            int nbtInt =  stack.getNbt().getInt(nbtKey);
            if (nbtInt == 3){
                return false;
            }
            return false;
        }
        return true;
    }
}
