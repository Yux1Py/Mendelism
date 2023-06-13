package com.yux1.mendelism.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;

public class ModUtils {
    public static void print(World world, PlayerEntity player, String string, Boolean actionBar){
        if (!world.isClient()){
            player.sendMessage(new LiteralText(string), actionBar);
        }
    }
}
