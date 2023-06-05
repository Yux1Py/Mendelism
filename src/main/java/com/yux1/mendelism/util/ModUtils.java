package com.yux1.mendelism.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;

public class ModUtils {
    public static void print(PlayerEntity player, LiteralText literalText, Boolean actionBar){
        player.sendMessage(literalText, actionBar);
    }
}
