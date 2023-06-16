package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.block.custom.ModPeaCropBlock;
import com.yux1.mendelism.item.ModItems;
import com.yux1.mendelism.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModPeaItem extends Item {

    private static Block cropBlock = null;

    public ModPeaItem(Block block, Settings settings) {
        super(settings);
        cropBlock = block;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Block block = world.getBlockState(pos).getBlock();
        PlayerEntity player = context.getPlayer();
        if (block == Blocks.FARMLAND){
            if (player != null) {
                ItemStack pea = player.getMainHandStack();
                ModPeaCropBlock cropBlock = (ModPeaCropBlock) ModBlocks.PEA_CROP;
                BlockState cropBlockState = cropBlock.getDefaultState();
                cropBlock.onPlaced(world, pos.up(), cropBlockState, player, pea);
                world.setBlockState(pos.up(), cropBlockState);
                pea.setCount(pea.getCount() - 1);
            }
            player.swingHand(Hand.MAIN_HAND);
            world.playSound(player, pos, SoundEvents.ITEM_CROP_PLANT,
                    SoundCategory.BLOCKS, 1f, 1f);
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (true){
            if(!Screen.hasShiftDown())
            {
                tooltip.add(new TranslatableText("item.mendelism.tooltip.shift"));
            }
            else
            {
                if (stack.getNbt() != null) {
                    tooltip.add(ModUtils.turnPeaNbtToTranslatableText(stack, "flower_color"));
                    tooltip.add(ModUtils.turnPeaNbtToTranslatableText(stack, "peel_shape"));
                    tooltip.add(ModUtils.turnPeaNbtToTranslatableText(stack, "peel_color"));
                    tooltip.add(ModUtils.turnPeaNbtToTranslatableText(stack, "seed_shape"));
                    tooltip.add(ModUtils.turnPeaNbtToTranslatableText(stack, "seed_color"));
                }
                else {
                    if (stack.getItem() == ModItems.PEA){
                        NbtCompound nbt = new NbtCompound();
                        nbt.putInt("flower_color", 1);
                        nbt.putInt("peel_shape", 1);
                        nbt.putInt("peel_color", 1);
                        nbt.putInt("seed_shape", 1);
                        nbt.putInt("seed_color", 1);
                        stack.setNbt(nbt);
                    }
                }
            }
        }
        else {
            tooltip.add(new TranslatableText("item.mendelism.tooltip.need_machine"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
