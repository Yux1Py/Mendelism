package com.yux1.mendelism.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.Objects;

//豌豆（种子）类
public class ModPeaItem extends BlockItem {

    public static NbtCompound[][] genotype = {};

    public static NbtCompound genotype_seed_color_1;
    public static NbtCompound genotype_seed_color_2;

    public static NbtCompound[] genotype_seed_color = {genotype_seed_color_1, genotype_seed_color_2};


    public ModPeaItem(Block block, Settings settings) {
        super(block, settings);
    }

    private static void putColorNbt(ItemStack itemStack){
        if (itemStack.getNbt() != null){
            if (Objects.equals(itemStack.getNbt().getString("pea.seed_color.1"), "green") ||
                    Objects.equals(itemStack.getNbt().getString("pea.seed_color.2"), "green")){
                NbtCompound nbt = new NbtCompound();
                nbt.putString("pea.seed_color", "green");
                itemStack.setNbt(nbt);
            }
            else if (Objects.equals(itemStack.getNbt().getString("pea.seed_color.1"), "yellow") &&
                    Objects.equals(itemStack.getNbt().getString("pea.seed_color.1-2"), "yellow")){
                NbtCompound nbt = new NbtCompound();
                nbt.putString("pea.seed_color", "yellow");
                itemStack.setNbt(nbt);
            }
        }
    }

}
