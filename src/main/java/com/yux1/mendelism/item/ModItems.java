package com.yux1.mendelism.item;

import com.yux1.mendelism.Mendelism;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {


    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Mendelism.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Mendelism.LOGGER.info("Registering Mod Items for " + Mendelism.MOD_ID);
    }
}
