package com.yux1.mendelism.item;

import com.yux1.mendelism.Mendelism;
import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.item.custom.ModPeaItem;
import com.yux1.mendelism.item.custom.ModPeaPodItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item PEA_POD = registerItem("pea_pod",
            new ModPeaPodItem(new FabricItemSettings().group(ModItemGroup.MENDELISM)));

    public static final Item PEA = registerItem("pea",
            new ModPeaItem(ModBlocks.PEA_CROP,
                    new FabricItemSettings().group(ModItemGroup.MENDELISM)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Mendelism.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Mendelism.LOGGER.info("Registering Mod Items for " + Mendelism.MOD_ID);
    }
}
