package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.util.ModModelPredicateProvider;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModPeaPodItem extends Item {
    public ModPeaPodItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();
        if (stack.hasNbt()){
            assert stack.getNbt() != null;
            int peel_color_old =  stack.getNbt().getInt("peel_color");
            int peel_color_new = (peel_color_old == 3) ? 1 : (peel_color_old + 1);
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("peel_color", peel_color_new);
            stack.setNbt(nbt);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()){
            assert stack.getNbt() != null;
            int peel_color =  stack.getNbt().getInt("peel_color");
            tooltip.add(new LiteralText(String.valueOf(peel_color)));
            boolean b = peel_color != 3;
            tooltip.add(new LiteralText(String.valueOf(b)));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
