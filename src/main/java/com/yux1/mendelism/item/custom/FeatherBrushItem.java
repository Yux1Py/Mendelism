package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.partical.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FeatherBrushItem extends Item {
    public FeatherBrushItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onCraft(ItemStack brush, World world, PlayerEntity player) {
        NbtCompound nbt = new NbtCompound();
        nbt.putBoolean("has_pollen", false);
        brush.setNbt(nbt);
        super.onCraft(brush, world, player);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (stack.getNbt() != null) {
            return stack.getNbt().getBoolean("has_pollen");
        } else return false;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack brush = context.getStack();
        if (!brush.hasNbt()) {
            NbtCompound nbt = new NbtCompound();
            nbt.putBoolean("has_pollen", false);
            brush.setNbt(nbt);
        }
        World world = context.getWorld();
        Block block = world.getBlockState(context.getBlockPos()).getBlock();
        if (!(block == ModBlocks.PEA_CROP)) {
            NbtCompound nbt = new NbtCompound();
            nbt.putBoolean("has_pollen", false);
            brush.setNbt(nbt);
            context.getPlayer().swingHand(Hand.MAIN_HAND);
            spawnFeatherParticles(world, context.getBlockPos(), 0.01d);
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack brush, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (brush.hasNbt()) {
            boolean bHasPollen = brush.getNbt().getBoolean("has_pollen");
            if (bHasPollen) {
                tooltip.add(new TranslatableText("item.mendelism.tooltip.has_pollen"));
            } else {
                tooltip.add(new TranslatableText("item.mendelism.tooltip.has_not_pollen"));
            }
        }
        super.appendTooltip(brush, world, tooltip, context);
    }

    private void spawnFeatherParticles(World world, BlockPos positionClicked, double velocityY) {
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0) {
                world.addParticle(ModParticles.FEATHER_PARTICLE,
                        positionClicked.getX() + 0.5d + Math.cos(i) * 0.02d,
                        positionClicked.getY() + 1.2d,
                        positionClicked.getZ() + 0.5d + Math.sin(i) * 0.02d,
                        Math.cos(i) * 0.02d, velocityY, Math.sin(i) * 0.02d);
            }
        }
    }
}
