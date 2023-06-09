package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack brush = context.getStack();
        if (!brush.hasNbt()){
            NbtCompound nbt = new NbtCompound();
            nbt.putBoolean("has_pollen", false);
            brush.setNbt(nbt);
        }
        World world = context.getWorld();
        Block block = world.getBlockState(context.getBlockPos()).getBlock();
        if (!(block == ModBlocks.PEA_CROP)){
            NbtCompound nbt = new NbtCompound();
            nbt.putBoolean("has_pollen", false);
            brush.setNbt(nbt);
            context.getPlayer().sendMessage(new LiteralText("清理羽毛刷"), false);
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack brush, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (brush.hasNbt()){
            boolean bHasPollen = brush.getNbt().getBoolean("has_pollen");
            tooltip.add(new LiteralText(String.valueOf(bHasPollen)));
        }
        super.appendTooltip(brush, world, tooltip, context);
    }
}
