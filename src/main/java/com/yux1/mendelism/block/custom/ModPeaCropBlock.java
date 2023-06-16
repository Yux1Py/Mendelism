package com.yux1.mendelism.block.custom;

import com.yux1.mendelism.item.ModItems;
import com.yux1.mendelism.util.ModTags;
import com.yux1.mendelism.util.ModUtils;
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
import net.minecraft.text.LiteralText;
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
    //果皮颜色  绿色·黄色
    //种皮形状  饱满·皱缩
    //种皮颜色  绿色·黄色
    private static final IntProperty B_FLOWER_COLOR = IntProperty.of("b_flower_color", 1, 3);
    private static final IntProperty B_PEEL_SHAPE = IntProperty.of("b_peel_shape", 1, 3);
    private static final IntProperty B_PEEL_COLOR = IntProperty.of("b_peel_color", 1, 3);
    private static final IntProperty B_SEED_SHAPE = IntProperty.of("b_seed_shape", 1, 3);
    private static final IntProperty B_SEED_COLOR = IntProperty.of("b_seed_color", 1, 3);

    private static final IntProperty P_FLOWER_COLOR = IntProperty.of("p_flower_color", 1, 3);
    private static final IntProperty P_PEEL_SHAPE = IntProperty.of("p_peel_shape", 1, 3);
    private static final IntProperty P_PEEL_COLOR = IntProperty.of("p_peel_color", 1, 3);
    private static final IntProperty P_SEED_SHAPE = IntProperty.of("p_seed_shape", 1, 3);
    private static final IntProperty P_SEED_COLOR = IntProperty.of("p_seed_color", 1, 3);

    private ItemStack seed;

    private boolean hasPollen;

    public ModPeaCropBlock(Settings settings) {
        super(settings);
        //默认有雄蕊，无花粉

        //默认基因型为
        // 红色花色纯合子
        // 饱满果皮纯合子
        // 绿色果皮纯合子
        // 饱满种皮纯合子
        // 绿色种皮纯合子
        hasPollen = false;
        setDefaultState(this.getStateManager().getDefaultState()
                .with(HAS_STAMEN, true)
                .with(HAS_POLLEN, false)

                .with(B_FLOWER_COLOR, 1)
                .with(B_PEEL_SHAPE, 1)
                .with(B_PEEL_COLOR, 1)
                .with(B_SEED_SHAPE, 1)
                .with(B_SEED_COLOR, 1)
                .with(P_FLOWER_COLOR, 1)
                .with(P_PEEL_SHAPE, 1)
                .with(P_PEEL_COLOR, 1)
                .with(P_PEEL_COLOR, 1)
                .with(P_SEED_COLOR, 1));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

        //将种子基因型继承至植株
        setDefaultState(this.getStateManager().getDefaultState()
                .with(HAS_STAMEN, true)
                .with(HAS_POLLEN, false)
                .with(B_FLOWER_COLOR, itemStack.getNbt().getInt("flower_color"))
                .with(B_PEEL_SHAPE, itemStack.getNbt().getInt("peel_shape"))
                .with(B_PEEL_COLOR, itemStack.getNbt().getInt("peel_color"))
                .with(B_SEED_SHAPE, itemStack.getNbt().getInt("seed_shape"))
                .with(B_SEED_COLOR, itemStack.getNbt().getInt("seed_color")));

        //保存一下种子方便掉落
        if (placer != null) {
            //绿色饱满
            if (state.get(B_SEED_SHAPE) != 3 && state.get(B_SEED_COLOR) != 3){
                ItemStack newSeed = new ItemStack(ModItems.PEA_GREEN_ROUND);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToNbt(state, B_FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToNbt(state, B_PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToNbt(state, B_PEEL_COLOR, nbt, "peel_color");
                blockPropertyToNbt(state, B_SEED_SHAPE, nbt, "seed_shape");
                blockPropertyToNbt(state, B_SEED_COLOR, nbt, "seed_color");
                newSeed.setNbt(nbt);
                seed = newSeed;
            }
            //绿色皱缩
            else if (state.get(B_SEED_SHAPE) == 3 && state.get(B_SEED_COLOR) != 3){
                ItemStack newSeed = new ItemStack(ModItems.PEA_GREEN_WRINKLED);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToNbt(state, B_FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToNbt(state, B_PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToNbt(state, B_PEEL_COLOR, nbt, "peel_color");
                blockPropertyToNbt(state, B_SEED_SHAPE, nbt, "seed_shape");
                blockPropertyToNbt(state, B_SEED_COLOR, nbt, "seed_color");
                newSeed.setNbt(nbt);
                seed = newSeed;
            }
            //黄色饱满
            else if (state.get(B_SEED_SHAPE) != 3 && state.get(B_SEED_COLOR) == 3){
                ItemStack newSeed = new ItemStack(ModItems.PEA_YELLOW_ROUND);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToNbt(state, B_FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToNbt(state, B_PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToNbt(state, B_PEEL_COLOR, nbt, "peel_color");
                blockPropertyToNbt(state, B_SEED_SHAPE, nbt, "seed_shape");
                blockPropertyToNbt(state, B_SEED_COLOR, nbt, "seed_color");
                newSeed.setNbt(nbt);
                seed = newSeed;
            }
            //黄色皱缩
            else if (state.get(B_SEED_SHAPE) == 3 && state.get(B_SEED_COLOR) == 3){
                ItemStack newSeed = new ItemStack(ModItems.PEA_YELLOW_WRINKLED);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToNbt(state, B_FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToNbt(state, B_PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToNbt(state, B_PEEL_COLOR, nbt, "peel_color");
                blockPropertyToNbt(state, B_SEED_SHAPE, nbt, "seed_shape");
                blockPropertyToNbt(state, B_SEED_COLOR, nbt, "seed_color");
                newSeed.setNbt(nbt);
                seed = newSeed;
            }
            else {
                ItemStack newSeed = new ItemStack(ModItems.PEA_GREEN_ROUND);
                NbtCompound nbt = new NbtCompound();
                blockPropertyToNbt(state, B_FLOWER_COLOR, nbt, "flower_color");
                blockPropertyToNbt(state, B_PEEL_SHAPE, nbt, "peel_shape");
                blockPropertyToNbt(state, B_PEEL_COLOR, nbt, "peel_color");
                blockPropertyToNbt(state, B_SEED_SHAPE, nbt, "seed_shape");
                blockPropertyToNbt(state, B_SEED_COLOR, nbt, "seed_color");
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
        builder.add(B_FLOWER_COLOR, B_PEEL_SHAPE, B_PEEL_COLOR, B_SEED_SHAPE, B_SEED_COLOR);
        builder.add(P_FLOWER_COLOR, P_PEEL_SHAPE, P_PEEL_COLOR, P_SEED_SHAPE, P_SEED_COLOR);
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
                    cropGrow(this, i, state, world, pos, random);
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
        //若未去雄，顺利执行其他操作
        //去雄
        if (Registry.ITEM.getOrCreateEntry(Registry.ITEM.getKey(player.getMainHandStack().getItem()).get()).isIn(ModTags.Items.EMASCULATION)){
            if (state.get(HAS_STAMEN)){
                //减少耐久
                if (!player.isCreative()){
                    player.getMainHandStack().setDamage(player.getMainHandStack().getMaxDamage() - 1);
                }
                ModUtils.print(world, player, "去雄", false);
                player.swingHand(Hand.MAIN_HAND);
                world.setBlockState(pos, state.with(HAS_STAMEN, false));
            }
        }
        //若已去雄，则阻止玩家催熟
        else if (!state.get(HAS_STAMEN) && !state.get(HAS_POLLEN) && !(Registry.ITEM.getOrCreateEntry(Registry.ITEM.getKey(player.getMainHandStack().getItem()).get()).isIn(ModTags.Items.BRUSH))){
            return ActionResult.SUCCESS;
        }
        //取花粉 或 授粉
        else if (Registry.ITEM.getOrCreateEntry(Registry.ITEM.getKey(player.getMainHandStack().getItem()).get()).isIn(ModTags.Items.BRUSH)){
            ItemStack brush = player.getMainHandStack();
            if (brush.hasNbt()){
                boolean bHasPollen = brush.getNbt().getBoolean("has_pollen");
                //若刷子中未存储花粉 并且 植株未进行授粉或去雄
                if (!world.isClient()){
                    player.sendMessage(new LiteralText(String.valueOf(state.get(HAS_STAMEN))), false);
                    player.sendMessage(new LiteralText(String.valueOf(state.get(HAS_POLLEN))), false);
                }
                if (!bHasPollen && !state.get(HAS_POLLEN) && state.get(HAS_STAMEN)){
                    ModUtils.print(world, player, "取花粉", false);
                    player.swingHand(Hand.MAIN_HAND);
                    NbtCompound nbt = new NbtCompound();
                    blockPropertyToNbt(state, B_FLOWER_COLOR, nbt, "flower_color");
                    blockPropertyToNbt(state, B_PEEL_SHAPE, nbt, "peel_shape");
                    blockPropertyToNbt(state, B_PEEL_COLOR, nbt, "peel_color");
                    blockPropertyToNbt(state, B_SEED_SHAPE, nbt, "seed_shape");
                    blockPropertyToNbt(state, B_SEED_COLOR, nbt, "seed_color");
                    nbt.putBoolean("has_pollen", true);
                    brush.setNbt(nbt);
                }
                //若刷子中存储了花粉 并且 植株未进行授粉且已去雄
                else if (bHasPollen && !state.get(HAS_POLLEN) && !state.get(HAS_STAMEN) && this.getAge(state) > 4){
                    ModUtils.print(world, player, "授粉", false);
                    player.swingHand(Hand.MAIN_HAND);
                    saveGenotype(state, world, pos, player, hand, hit, brush);
                    NbtCompound nbt = new NbtCompound();
                    nbt.putBoolean("has_pollen", false);
                    brush.setNbt(nbt);
                    hasPollen = true;
                }
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
            ItemStack peaPod = choosePeaPodItem(world, pos, state, player, hasPollen);
            Random random = new Random();
            int count = random.nextInt(1, 6);
            dropStack(world, pos, peaPod);
            if (count == 1) {}
            else if (count == 5){
                dropStack(world, pos, peaPod);
                dropStack(world, pos, peaPod);
            }
            else {
                dropStack(world, pos, peaPod);
            }
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

    private static void generalBlockPropertyToNbt(BlockState state, NbtCompound nbt){
        blockPropertyToNbt(state, B_FLOWER_COLOR, nbt, "b_flower_color");
        blockPropertyToNbt(state, B_PEEL_SHAPE, nbt, "b_peel_shape");
        blockPropertyToNbt(state, B_PEEL_COLOR, nbt, "b_peel_color");
        blockPropertyToNbt(state, B_SEED_SHAPE, nbt, "b_seed_shape");
        blockPropertyToNbt(state, B_SEED_COLOR, nbt, "b_seed_color");
        blockPropertyToNbt(state, P_FLOWER_COLOR, nbt, "p_flower_color");
        blockPropertyToNbt(state, P_PEEL_SHAPE, nbt, "p_peel_shape");
        blockPropertyToNbt(state, P_PEEL_COLOR, nbt, "p_peel_color");
        blockPropertyToNbt(state, P_SEED_SHAPE, nbt, "p_seed_shape");
        blockPropertyToNbt(state, P_SEED_COLOR, nbt, "p_seed_color");
    }

    private static void blockPropertyToNbt(BlockState state, IntProperty intProperty, NbtCompound nbt, String nbtKey){
        if (state.get(intProperty) == 1){
            nbt.putInt(nbtKey, 1);
        }
        else if (state.get(intProperty) == 2){
            nbt.putInt(nbtKey, 2);
        }
        else if (state.get(intProperty) == 3){
            nbt.putInt(nbtKey, 3);
        }
        else {
            nbt.putInt(nbtKey, 2);
        }
    }

    //将Crop的基因型继承至PeaPod，并选择豆荚材质
    private static ItemStack choosePeaPodItem(World world, BlockPos pos, BlockState state, PlayerEntity player, boolean hasPollen){
        int peelShape = state.get(B_PEEL_SHAPE);
        int peelColor = state.get(B_PEEL_COLOR);

        //绿色饱满
        if (peelShape != 3 && peelColor != 3){
            ItemStack peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
            NbtCompound nbt = new NbtCompound();
            generalBlockPropertyToNbt(state, nbt);
            nbt.putBoolean("has_pollen", hasPollen);
            peaPod.setNbt(nbt);
            return peaPod;
        }
        //绿色皱缩
        else if (peelShape == 3 && peelColor != 3){
            ItemStack peaPod = new ItemStack(ModItems.PEA_POD_GREEN_SHRUNKEN);
            NbtCompound nbt = new NbtCompound();
            generalBlockPropertyToNbt(state, nbt);
            nbt.putBoolean("has_pollen", hasPollen);
            peaPod.setNbt(nbt);
            return peaPod;
        }
        //黄色饱满
        else if (peelShape != 3 && peelColor == 3){
            ItemStack peaPod = new ItemStack(ModItems.PEA_POD_YELLOW_PLUMP);
            NbtCompound nbt = new NbtCompound();
            generalBlockPropertyToNbt(state, nbt);
            nbt.putBoolean("has_pollen", hasPollen);
            peaPod.setNbt(nbt);
            return peaPod;
        }
        //黄色皱缩
        else if (peelShape == 3 && peelColor == 3){
            ItemStack peaPod = new ItemStack(ModItems.PEA_POD_YELLOW_SHRUNKEN);
            NbtCompound nbt = new NbtCompound();
            generalBlockPropertyToNbt(state, nbt);
            nbt.putBoolean("has_pollen", hasPollen);
            peaPod.setNbt(nbt);
            return peaPod;
        }
        else {
            ItemStack peaPod = new ItemStack(ModItems.PEA_POD_GREEN_PLUMP);
            NbtCompound nbt = new NbtCompound();
            generalBlockPropertyToNbt(state, nbt);
            nbt.putBoolean("has_pollen", hasPollen);
            peaPod.setNbt(nbt);
            return peaPod;
        }
    }

    //在授粉之后进行BlockState内基因型的更新
    private void saveGenotype(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, ItemStack brush){
        assert brush.getNbt() != null;

        setDefaultState(this.getStateManager().getDefaultState()
                .with(HAS_STAMEN, false)
                .with(HAS_POLLEN, true)
                .with(P_FLOWER_COLOR, brush.getNbt().getInt("flower_color"))
                .with(P_PEEL_SHAPE,  brush.getNbt().getInt("peel_shape"))
                .with(P_PEEL_COLOR, brush.getNbt().getInt("peel_color"))
                .with(P_SEED_SHAPE, brush.getNbt().getInt("seed_shape"))
                .with(P_SEED_COLOR, brush.getNbt().getInt("seed_color")));
        world.setBlockState(pos, state.with(HAS_POLLEN, true));
    }

    private int calculateGene(int gene01, int gene02){
        int geneAddon = gene01 + gene02;
        Random random = new Random();
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
