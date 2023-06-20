package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.item.ModItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class ModPeaPodItem extends Item {
    public ModPeaPodItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack peaPod = user.getMainHandStack();
        dropPea(world, user, hand, peaPod);
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        if (stack.getNbt() != null) {
                    /*tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("b_flower_color"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("b_peel_shape"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("b_peel_color"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("b_seed_shape"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("b_seed_color"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("p_flower_color"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("p_peel_shape"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("p_peel_color"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("p_seed_shape"))));
                    tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("p_seed_color"))));*/
        }
        else {
            if (stack.getItem() == ModItems.PEA_POD){
                NbtCompound nbt = new NbtCompound();
                nbt.putBoolean("has_pollen", false);
                nbt.putInt("b_flower_color", 1);
                nbt.putInt("b_peel_shape", 1);
                nbt.putInt("b_peel_color", 1);
                nbt.putInt("b_seed_shape", 1);
                nbt.putInt("b_seed_color", 1);
                nbt.putInt("p_flower_color", 1);
                nbt.putInt("p_peel_shape", 1);
                nbt.putInt("p_peel_color", 1);
                nbt.putInt("p_seed_shape", 1);
                nbt.putInt("p_seed_color", 1);
                stack.setNbt(nbt);
            }
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    private static void dropPea(World world, PlayerEntity user, Hand hand, ItemStack peaPod){
        //豌豆掉落数量
        Random random = new Random();
        int count = random.nextInt(2, 4);
        //减少豌豆荚
        peaPod.setCount(peaPod.getCount() - 1);
        user.setStackInHand(hand, peaPod);
        user.swingHand(hand);
        //选择豌豆颜色并输入nbt
        assert peaPod.getNbt() != null;
        for (int i = 0; i < count; i++){
            int newFlowerColor = chooseGene(peaPod, "b_flower_color", "p_flower_color");
            int newPeelShape = chooseGene(peaPod, "b_peel_shape", "p_peel_shape");
            int newPeelColor = chooseGene(peaPod, "b_peel_color", "p_peel_color");
            int newSeedShape = chooseGene(peaPod, "b_seed_shape", "p_seed_shape");
            int newSeedColor = chooseGene(peaPod, "b_seed_color", "p_seed_color");
            //绿色饱满
            if (newSeedShape != 3 && newSeedColor != 3){
                ItemStack pea = new ItemStack(ModItems.PEA_GREEN_ROUND);
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("flower_color", newFlowerColor);
                nbtCompound.putInt("peel_shape", newPeelShape);
                nbtCompound.putInt("peel_color", newPeelColor);
                nbtCompound.putInt("seed_shape", newSeedShape);
                nbtCompound.putInt("seed_color", newSeedColor);
                pea.setNbt(nbtCompound);
                user.giveItemStack(pea);
            }
            //绿色皱缩
            else if (newSeedShape == 3 && newSeedColor != 3){
                ItemStack pea = new ItemStack(ModItems.PEA_GREEN_WRINKLED);
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("flower_color", newFlowerColor);
                nbtCompound.putInt("peel_shape", newPeelShape);
                nbtCompound.putInt("peel_color", newPeelColor);
                nbtCompound.putInt("seed_shape", newSeedShape);
                nbtCompound.putInt("seed_color", newSeedColor);
                pea.setNbt(nbtCompound);
                user.giveItemStack(pea);
            }
            //黄色饱满
            else if (newSeedShape != 3 && newSeedColor == 3){
                ItemStack pea = new ItemStack(ModItems.PEA_YELLOW_ROUND);
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("flower_color", newFlowerColor);
                nbtCompound.putInt("peel_shape", newPeelShape);
                nbtCompound.putInt("peel_color", newPeelColor);
                nbtCompound.putInt("seed_shape", newSeedShape);
                nbtCompound.putInt("seed_color", newSeedColor);
                pea.setNbt(nbtCompound);
                user.giveItemStack(pea);
            }
            //黄色皱缩
            else if (newSeedShape == 3 && newSeedColor == 3){
                ItemStack pea = new ItemStack(ModItems.PEA_YELLOW_WRINKLED);
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("flower_color", newFlowerColor);
                nbtCompound.putInt("peel_shape", newPeelShape);
                nbtCompound.putInt("peel_color", newPeelColor);
                nbtCompound.putInt("seed_shape", newSeedShape);
                nbtCompound.putInt("seed_color", newSeedColor);
                pea.setNbt(nbtCompound);
                user.giveItemStack(pea);
            }

        }

    }

    private static int chooseGene(ItemStack peaPod, String keyB, String keyP){
        Random random = new Random();
        assert peaPod.getNbt() != null;
        int gene01 = peaPod.getNbt().getInt(keyB);
        int gene02 = peaPod.getNbt().getInt(keyP);
        int possibility = random.nextInt(1, 5);
        if (gene01 == 1 && gene02 == 1){
            return 1;
        }
        else if ((gene01 == 1 && gene02 == 2) || (gene01 == 2 && gene02 == 1)){
            if (possibility < 3){
                return 1;
            }
            else {
                return 2;
            }
        }
        else if ((gene01 == 3 && gene02 == 2) || (gene01 == 2 && gene02 == 3)){
            if (possibility < 3){
                return 3;
            }
            else {
                return 2;
            }
        }
        else if (gene01 == 3 && gene02 == 3){
            return 3;
        }
        else if (gene01 == 2 && gene02 == 2){
            if (possibility == 1){
                return 1;
            }
            else if (possibility == 4){
                return 3;
            }
            else {
                return 2;
            }
        }
        else if ((gene01 == 1 && gene02 == 3) ||
                (gene01 == 3 && gene02 == 1)){
            return 2;
        }
        else {
            return 1;
        }
    }
}
