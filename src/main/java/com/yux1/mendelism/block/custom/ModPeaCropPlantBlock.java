package com.yux1.mendelism.block.custom;

import com.yux1.mendelism.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModPeaCropPlantBlock extends PlantBlock {

    public enum Genotype{
        NONE,
        FLOWER_COLOR,
        PEEL_SHAPE,
        PEEL_COLOR,
        SEED_SHAPE,
        SEED_COLOR
    }

    private Genotype genotype;

    public ModPeaCropPlantBlock(Settings settings, Genotype genotype) {
        super(settings);
        this.genotype = genotype;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        dropPeaPod(world, pos, state, player);
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        dropPeaPod(world, pos, state, player);
    }

    private void dropPeaPod(World world, BlockPos pos, BlockState state, PlayerEntity player){
        ItemStack peaPod;
        if (!player.isCreative()){
            if (genotype == Genotype.NONE){
                peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("flower_color", 1);
                nbt.putInt("peel_shape", 1);
                nbt.putInt("peel_color", 1);
                nbt.putInt("seed_shape", 1);
                nbt.putInt("seed_color", 1);
                peaPod.setNbt(nbt);
                dropStack(world, pos, peaPod);
            }
            else if (genotype == Genotype.FLOWER_COLOR){
                peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("flower_color", 3);
                nbt.putInt("peel_shape", 1);
                nbt.putInt("peel_color", 1);
                nbt.putInt("seed_shape", 1);
                nbt.putInt("seed_color", 1);
                peaPod.setNbt(nbt);
                dropStack(world, pos, peaPod);
            }
            else if (genotype == Genotype.PEEL_SHAPE){
                peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("flower_color", 1);
                nbt.putInt("peel_shape", 3);
                nbt.putInt("peel_color", 1);
                nbt.putInt("seed_shape", 1);
                nbt.putInt("seed_color", 1);
                peaPod.setNbt(nbt);
                dropStack(world, pos, peaPod);
            }
            else if (genotype == Genotype.PEEL_COLOR){
                peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("flower_color", 1);
                nbt.putInt("peel_shape", 1);
                nbt.putInt("peel_color", 3);
                nbt.putInt("seed_shape", 1);
                nbt.putInt("seed_color", 1);
                peaPod.setNbt(nbt);
                dropStack(world, pos, peaPod);
            }
            else if (genotype == Genotype.SEED_SHAPE){
                peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("flower_color", 1);
                nbt.putInt("peel_shape", 1);
                nbt.putInt("peel_color", 1);
                nbt.putInt("seed_shape", 3);
                nbt.putInt("seed_color", 1);
                peaPod.setNbt(nbt);
                dropStack(world, pos, peaPod);
            }
            else if (genotype == Genotype.SEED_COLOR){
                peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("flower_color", 1);
                nbt.putInt("peel_shape", 1);
                nbt.putInt("peel_color", 1);
                nbt.putInt("seed_shape", 1);
                nbt.putInt("seed_color", 3);
                peaPod.setNbt(nbt);
                dropStack(world, pos, peaPod);
            }
        }
    }
}
