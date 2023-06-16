package com.yux1.mendelism.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.Objects;

public class ModUtils {
    public static void print(World world, PlayerEntity player, String string, Boolean actionBar){
        if (!world.isClient()){
            player.sendMessage(new LiteralText(string), actionBar);
        }
    }

    public static TranslatableText turnPeaNbtToTranslatableText(ItemStack stack, String key){
        assert stack.getNbt() != null;
        if (Objects.equals(key, "flower_color")){
            if (stack.getNbt().getInt(key) == 1){
                return new TranslatableText("item.mendelism.pea.tooltip.flower_color_dominant");
            }
            else if (stack.getNbt().getInt(key) == 2){
                return new TranslatableText("item.mendelism.pea.tooltip.flower_color_hybrid");
            }
            else if (stack.getNbt().getInt(key) == 3){
                return new TranslatableText("item.mendelism.pea.tooltip.flower_color_recessive");
            }
        }
        else if (Objects.equals(key, "peel_shape")){
            if (stack.getNbt().getInt(key) == 1){
                return new TranslatableText("item.mendelism.pea.tooltip.peel_shape_dominant");
            }
            else if (stack.getNbt().getInt(key) == 2){
                return new TranslatableText("item.mendelism.pea.tooltip.peel_shape_hybrid");
            }
            else if (stack.getNbt().getInt(key) == 3){
                return new TranslatableText("item.mendelism.pea.tooltip.peel_shape_recessive");
            }
        }
        else if (Objects.equals(key, "peel_color")){
            if (stack.getNbt().getInt(key) == 1){
                return new TranslatableText("item.mendelism.pea.tooltip.peel_color_dominant");
            }
            else if (stack.getNbt().getInt(key) == 2){
                return new TranslatableText("item.mendelism.pea.tooltip.peel_color_hybrid");
            }
            else if (stack.getNbt().getInt(key) == 3){
                return new TranslatableText("item.mendelism.pea.tooltip.peel_color_recessive");
            }
        }
        else if (Objects.equals(key, "seed_shape")){
            if (stack.getNbt().getInt(key) == 1){
                return new TranslatableText("item.mendelism.pea.tooltip.seed_shape_dominant");
            }
            else if (stack.getNbt().getInt(key) == 2){
                return new TranslatableText("item.mendelism.pea.tooltip.seed_shape_hybrid");
            }
            else if (stack.getNbt().getInt(key) == 3){
                return new TranslatableText("item.mendelism.pea.tooltip.seed_shape_recessive");
            }
        }
        else if (Objects.equals(key, "seed_color")){
            if (stack.getNbt().getInt(key) == 1){
                return new TranslatableText("item.mendelism.pea.tooltip.seed_color_dominant");
            }
            else if (stack.getNbt().getInt(key) == 2){
                return new TranslatableText("item.mendelism.pea.tooltip.seed_color_hybrid");
            }
            else if (stack.getNbt().getInt(key) == 3){
                return new TranslatableText("item.mendelism.pea.tooltip.seed_color_recessive");
            }
        }
        return null;
    }
}
