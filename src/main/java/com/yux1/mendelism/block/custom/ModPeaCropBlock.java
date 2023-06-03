package com.yux1.mendelism.block.custom;

import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

//豌豆作物类
public class ModPeaCropBlock extends CropBlock {

    //是否有雄蕊
    public static final BooleanProperty HAS_STAMEN = BooleanProperty.of("has_stamen");

    //是否有花粉输入
    public static final BooleanProperty HAS_POLLEN = BooleanProperty.of("has_pollen");

    //花粉的基因型
    //public static final

    //基因型
    public static final BooleanProperty GENOTYPE_SEED_COLOR_1 = BooleanProperty.of("genotype_seed_color_1");
    public static final BooleanProperty GENOTYPE_SEED_COLOR_2 = BooleanProperty.of("genotype_seed_color_2");


    public ModPeaCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);

            //如果还没开花，正常生长
            if (i < 5) {
                cropGrow(this, i, state, world, pos, random);
            }
            //如果到达花期
            else if (i >= 5 && i < this.getMaxAge()){

                //如果有雄蕊（未做去雄处理），继续正常生长
                if (state.get(HAS_STAMEN)){
                    cropGrow(this, i, state, world, pos, random);
                }
                //如果有花粉输入（去雄后），继续生长并输入花粉基因
                else if (!state.get(HAS_STAMEN) && state.get(HAS_POLLEN)){

                }
            }
        }

    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        //在放置时将方块的雄蕊状态设置为有雄蕊
        if (!world.isClient()){
            world.setBlockState(pos, state.with(HAS_STAMEN, true), Block.NOTIFY_ALL);
        }
        //在放置时将种子的基因型加载上去
        ItemStack pea = ((PlayerEntity) placer).getStackInHand(placer.getActiveHand());
        if (!world.isClient()){
            setPeaCropBooleanProperty(world, pos, state, pea, GENOTYPE_SEED_COLOR_1);
            setPeaCropBooleanProperty(world, pos, state, pea, GENOTYPE_SEED_COLOR_2);
        }
    }

    //当被破坏时，掉落物品
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        dropItemStack(world, pos, state, player);
    }

    //作物生长
    private static void cropGrow(ModPeaCropBlock block, int thisAge, BlockState state, ServerWorld world, BlockPos pos, Random random){
        float f = getAvailableMoisture(block, world, pos);
        if (random.nextInt((int)(25.0F / f) + 1) == 0) {
            world.setBlockState(pos, block.withAge(thisAge + 1), 2);
        }
    }

    //掉落物品的方块
    private void dropItemStack(World world, BlockPos pos, BlockState state, PlayerEntity player){
        if (this.getAge(state) == 0 || this.getAge(state) == 1 || this.getAge(state) == 2){
            ItemStack pea = new ItemStack(ModItems.PEA);
            setPeaItemNbt(state, pea, "pea.seed_color.1", GENOTYPE_SEED_COLOR_1);
            setPeaItemNbt(state, pea, "pea.seed_color.2", GENOTYPE_SEED_COLOR_2);
            dropStack(world, pos, pea);
        }
    }

    //设置豌豆的nbt属性
    private static void setPeaItemNbt(BlockState state, ItemStack pea, String key, BooleanProperty booleanProperty){
        NbtCompound seedColorNbt1 = new NbtCompound();
        //seedColorNbt1.putString(key, turnBooleanPropertyIntoString(state, booleanProperty));
        seedColorNbt1.putString(key, "yellow");
        pea.setNbt(seedColorNbt1);
    }

    //设置豌豆植株的BoolProperty
    private static void setPeaCropBooleanProperty(World world, BlockPos pos, BlockState state, ItemStack pea, BooleanProperty booleanProperty){
        world.setBlockState(pos, state.with(booleanProperty, turnStringIntoBooleanProperty(state, booleanProperty, pea)), Block.NOTIFY_ALL);
    }

    //将对应的Bool Property转化为可录入nbt的String类型
    private static String turnBooleanPropertyIntoString(BlockState state, BooleanProperty booleanProperty){
        if (booleanProperty.equals(GENOTYPE_SEED_COLOR_1)){
            if (state.get(booleanProperty)){
                return "green";
            }
            else {
                return "yellow";
            }
        }
        return "null";
    }

    //将对应的可录入nbt的String转化为Bool Property的Bool
    private static Boolean turnStringIntoBooleanProperty(BlockState state, BooleanProperty booleanProperty, ItemStack pea){
        if (booleanProperty.equals(GENOTYPE_SEED_COLOR_1)){
            if (Objects.equals(pea.getNbt().getString("pea.seed_color.1"), "green")){
                return true;
            }
            else if (Objects.equals(pea.getNbt().getString("pea.seed_color.1"), "yellow")){
                return false;
            }
        }
        return true;
    }
}
