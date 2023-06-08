package com.yux1.mendelism.block.custom;

import com.yux1.mendelism.item.ModItems;
import com.yux1.mendelism.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ModPeaCropBlock extends CropBlock {

    //是否有雄蕊
    public static final BooleanProperty HAS_STAMEN = BooleanProperty.of("has_stamen");

    //是否有花粉输入
    public static final BooleanProperty HAS_POLLEN = BooleanProperty.of("has_pollen");

    //花粉的基因型
    //public static final IntProperty

    //基因型，1代表显性纯合子， 2代表杂合子， 3代表隐性纯合子
    //
    //植株花色  红色·白色
    //果皮形状  饱满·皱缩
    //种皮颜色  绿色·黄色
    private static final IntProperty FLOWER_COLOR = IntProperty.of("flower_color", 1, 3);
    private static final IntProperty PEEL_SHAPE = IntProperty.of("peel_shape", 1, 3);
    private static final IntProperty SEED_COLOR = IntProperty.of("seed_color", 1, 3);

    private static ItemStack seed;

    public ModPeaCropBlock(Settings settings) {
        super(settings);
        //默认有雄蕊，无花粉
        setDefaultState(this.getStateManager().getDefaultState().with(HAS_STAMEN, true));
        setDefaultState(this.getStateManager().getDefaultState().with(HAS_POLLEN, false));
        //默认基因型为
        // 红色花色纯合子
        // 饱满果皮纯合子
        // 绿色种皮纯合子
        setDefaultState(this.getStateManager().getDefaultState().with(FLOWER_COLOR, 3));
        setDefaultState(this.getStateManager().getDefaultState().with(PEEL_SHAPE, 1));
        setDefaultState(this.getStateManager().getDefaultState().with(SEED_COLOR, 1));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        //将种子基因型继承至植株
        if (placer != null) {
            if (itemStack.getNbt() != null){
                /*setDefaultState(this.getStateManager().getDefaultState().with(FLOWER_COLOR, 3));
                setDefaultState(this.getStateManager().getDefaultState().with(PEEL_SHAPE, 1));
                setDefaultState(this.getStateManager().getDefaultState().with(SEED_COLOR, 3));*/
                /*world.setBlockState(pos, state.with(FLOWER_COLOR, 3));
                world.setBlockState(pos, state.with(PEEL_SHAPE, 3));
                world.setBlockState(pos, state.with(SEED_COLOR, 3));*/
            }
        }

        //保存一下种子方便掉落
        if (placer != null) {
            if (state.get(SEED_COLOR) == 1 || state.get(SEED_COLOR) == 2){
                ItemStack newSeed = new ItemStack(ModItems.PEA_GREEN_ROUND);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToPeaPodNbt(state, FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToPeaPodNbt(state, PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToPeaPodNbt(state, SEED_COLOR, nbt, "seed_color");
                newSeed.setNbt(nbt);
                seed = newSeed;
            }
            else if (state.get(SEED_COLOR) == 3){
                ItemStack newSeed = new ItemStack(ModItems.PEA_YELLOW_ROUND);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToPeaPodNbt(state, FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToPeaPodNbt(state, PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToPeaPodNbt(state, SEED_COLOR, nbt, "seed_color");
                newSeed.setNbt(nbt);
                seed = newSeed;
            }
            else {
                ItemStack newSeed = new ItemStack(ModItems.PEA_GREEN_ROUND);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToPeaPodNbt(state, FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToPeaPodNbt(state, PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToPeaPodNbt(state, SEED_COLOR, nbt, "seed_color");
                newSeed.setNbt(nbt);
                seed = newSeed;
            }
        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }

    //别忘了添加！！
    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_STAMEN, HAS_POLLEN);
        builder.add(FLOWER_COLOR, PEEL_SHAPE, SEED_COLOR);
        super.appendProperties(builder);
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

    //当被玩家破坏时，掉落豆荚
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        dropPeaPod(world, pos, state, player);
    }

    //当玩家右键使用时，若手持剪刀则去雄
    //             ，若不是剪刀且已成熟则掉落豆荚
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //若已去雄，则阻止玩家催熟
        if (!state.get(HAS_STAMEN)){
            return ActionResult.SUCCESS;
        }

        //若未去雄，顺利执行其他操作
        //去雄
        if (Registry.ITEM.getOrCreateEntry(Registry.ITEM.getKey(player.getMainHandStack().getItem()).get()).isIn(ModTags.Items.EMASCULATION)){
            if (!world.isClient()){
                player.swingHand(Hand.MAIN_HAND);
                world.setBlockState(pos, state.with(HAS_STAMEN, false));
            }
        }
        //若成熟，则收获
        else {
            if (this.getAge(state) == this.getMaxAge()){
                world.breakBlock(pos, false, player);
                player.swingHand(Hand.MAIN_HAND);
                dropPeaPod(world, pos, state, player);
            }
        }
        return ActionResult.PASS;
    }

    //掉落豆荚的方法
    private void dropPeaPod(World world, BlockPos pos, BlockState state, PlayerEntity player){
        if (this.getAge(state) == this.getMaxAge()){
            ItemStack peaPod = choosePeaPodItem(world, pos, state, player);
            dropStack(world, pos, peaPod);
        }
        else {
            dropStack(world, pos, seed);
        }
    }

    private static void cropGrow(ModPeaCropBlock block, int thisAge, BlockState state, ServerWorld world, BlockPos pos, Random random){
        float f = getAvailableMoisture(block, world, pos);
        if (random.nextInt((int)(25.0F / f) + 1) == 0) {
            world.setBlockState(pos, block.withAge(thisAge + 1), 2);
        }
    }

    private static void blockPropertyToPeaPodNbt(BlockState state, IntProperty intProperty, NbtCompound nbt, String nbtKey){
        switch (state.get(intProperty)){
            case 1:
                nbt.putInt(nbtKey, 1);
            case 2:
                nbt.putInt(nbtKey, 2);
            case 3:
                nbt.putInt(nbtKey, 3);
            default:
                nbt.putInt(nbtKey, 1);
        }

    }

    //将Crop的基因型继承至PeaPod
    private static ItemStack choosePeaPodItem(World world, BlockPos pos, BlockState state, PlayerEntity player){
        int peelShape = state.get(PEEL_SHAPE);
        switch (peelShape){
            case 1, 2:{
                ItemStack peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToPeaPodNbt(state, FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToPeaPodNbt(state, PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToPeaPodNbt(state, SEED_COLOR, nbt, "seed_color");
                peaPod.setNbt(nbt);
                return peaPod;
            }
            case 3:{
                ItemStack peaPod = new ItemStack(ModItems.PEA_POD_GREEN_SHRUNKEN);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToPeaPodNbt(state, FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToPeaPodNbt(state, PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToPeaPodNbt(state, SEED_COLOR, nbt, "seed_color");
                peaPod.setNbt(nbt);
                return peaPod;
            }
            default:
                return null;
        }
    }

}
