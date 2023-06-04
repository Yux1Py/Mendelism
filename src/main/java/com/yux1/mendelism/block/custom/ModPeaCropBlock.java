package com.yux1.mendelism.block.custom;

import com.yux1.mendelism.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
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
    //public static final

    //基因型
    //public static final

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
                //if (!world.isClient()){
                    //如果有雄蕊（未做去雄处理），继续正常生长
                    if (state.get(HAS_STAMEN)){
                        cropGrow(this, i, state, world, pos, random);
                    }
                    //如果有花粉输入（去雄后），继续生长并输入花粉基因
                    else if (!state.get(HAS_STAMEN) && state.get(HAS_POLLEN)){

                    }
                //}
            }
        }

    }

    //放置时的方法
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        //在放置时将方块的雄蕊状态设置为有雄蕊，并且无花粉输入
        if (!world.isClient()){
            world.setBlockState(pos, state.with(HAS_STAMEN, true), Block.NOTIFY_ALL);
            world.setBlockState(pos, state.with(HAS_POLLEN, false), Block.NOTIFY_ALL);
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
        //若已去雄，则组织玩家催熟
        //if (!state.get(HAS_STAMEN)){
         //   return ActionResult.SUCCESS;
        //}

        //若未去雄，顺利执行其他操作
        //if (!world.isClient()){player.sendMessage(new LiteralText(player.getStackInHand(Hand.MAIN_HAND).getItem().toString()), false);}
        if (Registry.ITEM.getOrCreateEntry(Registry.ITEM.getKey(player.getMainHandStack().getItem()).get()).isIn(ModTags.Items.EMASCULATION)){
            if (!world.isClient()){
                world.setBlockState(pos, state.with(HAS_STAMEN, false));
                player.sendMessage(new LiteralText("去雄"), false);
            }
        }
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
    private static void dropPeaPod(World world, BlockPos pos, BlockState state, PlayerEntity player){
        if (!world.isClient()){
            player.sendMessage(new LiteralText("掉落豆荚"), false);
        }
    }

    private static void cropGrow(ModPeaCropBlock block, int thisAge, BlockState state, ServerWorld world, BlockPos pos, Random random){
        float f = getAvailableMoisture(block, world, pos);
        if (random.nextInt((int)(25.0F / f) + 1) == 0) {
            world.setBlockState(pos, block.withAge(thisAge + 1), 2);
        }
    }
}
