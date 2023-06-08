package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class aaa extends Item {
    public aaa(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack pea = new ItemStack(ModItems.PEA_YELLOW_ROUND);
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("flower_color", 2);
        nbt.putInt("peel_shape", 2);
        nbt.putInt("peel_color", 2);
        nbt.putInt("seed_shape", 2);
        nbt.putInt("seed_color", 2);
        pea.setNbt(nbt);
        user.giveItemStack(pea);
        return super.use(world, user, hand);
    }
}
