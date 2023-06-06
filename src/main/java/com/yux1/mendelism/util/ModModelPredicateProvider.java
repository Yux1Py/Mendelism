package com.yux1.mendelism.util;

import com.yux1.mendelism.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        ModelPredicateProviderRegistry.register(peaPod, new Identifier("peel_color"),
                new UnclampedModelPredicateProvider(){

                    @Override
                    public float unclampedCall(ItemStack itemStack, @Nullable ClientWorld clientWorld, @Nullable LivingEntity livingEntity, int i) {
                        Entity entity = livingEntity != null ? livingEntity : itemStack.getHolder();
                        if (entity == null) {
                            return 1.0f;
                        } else {
                            if (clientWorld == null && ((Entity)entity).world instanceof ClientWorld) {
                                clientWorld = (ClientWorld)((Entity)entity).world;
                            }

                            if (clientWorld == null) {
                                return 1.0f;
                            } else {
                                int seed_color = itemStack.getNbt().getInt("seed_color");
                                boolean b = seed_color != 3;
                                return b ? 1.0f : 0.0f;}
                        }
                    }
                });
    }

    public static void registerModModels(){
        //registerPea();
        registerPeaPod(ModItems.PEA_POD);
    }

    public static boolean checkNbt(ItemStack stack, World world, LivingEntity entity, int seed, String nbtKey){
        if (stack.hasNbt()){
            if (entity != null){
                assert stack.getNbt() != null;
                int nbtInt =  stack.getNbt().getInt(nbtKey);
                if (nbtInt == 3){
                    return false;
                }
            }
        }
        return true;
    }

}
