package com.yux1.mendelism.item.custom;

import com.yux1.mendelism.item.ModItems;
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
            tooltip.add(new LiteralText(String.valueOf(stack.getNbt().getInt("seed_color"))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    private static void dropPea(World world, PlayerEntity user, Hand hand, ItemStack peaPod){
        //豌豆掉落数量
        Random random = new Random();
        int count = random.nextInt(3, 4);
        //减少豌豆荚
        peaPod.setCount(peaPod.getCount() - 1);
        user.setStackInHand(hand, peaPod);
        user.swingHand(hand);
        //选择豌豆颜色并输入nbt
        int peaPodSeedColor;
        if (peaPod.getNbt() != null) {
            peaPodSeedColor = peaPod.getNbt().getInt("seed_color");
        }
        else {
            peaPodSeedColor = 1;
        };

        if (peaPodSeedColor != 3){
            for (int i = 0; i < count; i++){
                ItemStack pea = new ItemStack(ModItems.PEA_GREEN_ROUND);
                int newFlowerColor = chooseGene(peaPod, "flower_color");
                int newPeelShape = chooseGene(peaPod, "peel_shape");
                int newSeedColor = chooseGene(peaPod, "seed_color");
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("flower_color", newFlowerColor);
                nbtCompound.putInt("peel_shape", newPeelShape);
                nbtCompound.putInt("seed_color", newSeedColor);
                pea.setNbt(nbtCompound);
                user.giveItemStack(pea);
            }
        }
        else {
            for (int i = 0; i < count; i++){
                ItemStack pea = new ItemStack(ModItems.PEA_YELLOW_ROUND);
                int newFlowerColor = chooseGene(peaPod, "flower_color");
                int newPeelShape = chooseGene(peaPod, "peel_shape");
                int newSeedColor = chooseGene(peaPod, "seed_color");
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("flower_color", newFlowerColor);
                nbtCompound.putInt("peel_shape", newPeelShape);
                nbtCompound.putInt("seed_color", newSeedColor);
                pea.setNbt(nbtCompound);
                user.giveItemStack(pea);
            }
        }
    }

    private static int chooseGene(ItemStack peaPod, String key){
        int oldGene;
        if (peaPod.getNbt() != null) {
            oldGene = peaPod.getNbt().getInt(key);
        }
        else {
            oldGene = 1;
        }

        if (oldGene == 1){
            return 1;
        }
        else if (oldGene == 2){
            Random random = new Random();
            float probability = random.nextFloat();
            if (probability < 0.25){
                return 1;
            }
            else if (probability > 0.75){
                return 3;
            }
            else {
                return 2;
            }
        }
        else if (oldGene == 3){
            return 3;
        }
        else {
            return 1;
        }
    }
}
