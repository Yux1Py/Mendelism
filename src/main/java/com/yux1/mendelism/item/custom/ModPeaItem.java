package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.block.custom.ModPeaCropBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModPeaItem extends AliasedBlockItem {

    private static Block cropBlock = null;

    public ModPeaItem(Block block, Settings settings) {
        super(block, settings);
        cropBlock = block;
    }

    /*@Override
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
                world.setBlockState(pos.up(), cropBlockState);
                cropBlock.onPlaced(world, pos.up(), cropBlockState, player, pea);
                pea.setCount(pea.getCount() - 1);
            }
            player.swingHand(Hand.MAIN_HAND);
            world.playSound(player, pos, SoundEvents.ITEM_CROP_PLANT,
                    SoundCategory.BLOCKS, 1f, 1f);
        }
        return super.useOnBlock(context);
    }*/

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getNbt() != null) {
            tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("seed_color"))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
