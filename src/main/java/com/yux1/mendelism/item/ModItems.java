package com.yux1.mendelism.item;

import com.yux1.mendelism.Mendelism;
import com.yux1.mendelism.block.ModBlocks;
import com.yux1.mendelism.item.custom.FeatherBrushItem;
import com.yux1.mendelism.item.custom.ModPeaItem;
import com.yux1.mendelism.item.custom.ModPeaPodItem;
import com.yux1.mendelism.item.custom.aaa;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.StewItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item PEA_POD = registerItem("pea_pod",
            new ModPeaPodItem(new FabricItemSettings().group(ModItemGroup.MENDELISM)));

    //绿色饱满豌豆荚
    public static final Item PEA_POD_GREEN_PLUMP = registerItem("pea_pod_green_plump",
            new ModPeaPodItem(new FabricItemSettings().group(null)));

    //绿色皱缩豌豆荚
    public static final Item PEA_POD_GREEN_SHRUNKEN = registerItem("pea_pod_green_shrunken",
            new ModPeaPodItem(new FabricItemSettings().group(null)));

    //黄色饱满豌豆荚
    public static final Item PEA_POD_YELLOW_PLUMP = registerItem("pea_pod_yellow_plump",
            new ModPeaPodItem(new FabricItemSettings().group(null)));

    //黄色饱满豌豆荚
    public static final Item PEA_POD_YELLOW_SHRUNKEN = registerItem("pea_pod_yellow_shrunken",
            new ModPeaPodItem(new FabricItemSettings().group(null)));


    public static final Item PEA = registerItem("pea",
            new ModPeaItem(ModBlocks.PEA_CROP,
                    new FabricItemSettings().food(ModFoodComponents.PEA)
                            .group(ModItemGroup.MENDELISM)));

    //绿色饱满
    public static final Item PEA_GREEN_ROUND = registerItem("pea_green_round",
            new ModPeaItem(ModBlocks.PEA_CROP,
                    new FabricItemSettings().food(ModFoodComponents.PEA)
                            .group(null)));

    //绿色皱缩
    public static final Item PEA_GREEN_WRINKLED = registerItem("pea_green_wrinkled",
            new ModPeaItem(ModBlocks.PEA_CROP,
                    new FabricItemSettings().food(ModFoodComponents.PEA)
                            .group(null)));

    //黄色饱满
    public static final Item PEA_YELLOW_ROUND = registerItem("pea_yellow_round",
            new ModPeaItem(ModBlocks.PEA_CROP,
                    new FabricItemSettings().food(ModFoodComponents.PEA)
                            .group(null)));

    //黄色皱缩
    public static final Item PEA_YELLOW_WRINKLED = registerItem("pea_yellow_wrinkled",
            new ModPeaItem(ModBlocks.PEA_CROP,
                    new FabricItemSettings().food(ModFoodComponents.PEA)
                            .group(null)));

    //豌豆糕（绿·黄）
    public static final Item PEA_CAKE_GREEN = registerItem("pea_cake_green",
            new Item(new FabricItemSettings().food(ModFoodComponents.PEA_CAKE)
                    .group(ModItemGroup.MENDELISM)));

    public static final Item PEA_CAKE_YELLOW = registerItem("pea_cake_yellow",
            new Item(new FabricItemSettings().food(ModFoodComponents.PEA_CAKE)
                    .group(ModItemGroup.MENDELISM)));

    //豌豆浓汤（绿·黄）
    public static final Item PEA_THICK_SOUP_GREEN = registerItem("pea_thick_soup_green",
            new StewItem(new FabricItemSettings().maxCount(1).food(ModFoodComponents.PEA_THICK_SOUP)
                    .group(ModItemGroup.MENDELISM)));

    public static final Item PEA_THICK_SOUP_YELLOW = registerItem("pea_thick_soup_yellow",
            new StewItem(new FabricItemSettings().maxCount(1).food(ModFoodComponents.PEA_THICK_SOUP)
                    .group(ModItemGroup.MENDELISM)));

    //羽毛刷
    public static final Item FEATHER_BRUSH = registerItem("feather_brush",
            new FeatherBrushItem(new FabricItemSettings().maxCount(1)
                    .group(ModItemGroup.MENDELISM)));

    //基因查看仪
    public static final Item GENOTYPE_VIEWING_MACHINE = registerItem("genotype_viewing_machine",
            new Item(new FabricItemSettings().maxCount(1)
                    .group(ModItemGroup.MENDELISM)));

    public static final Item AAA = registerItem("aaa",
            new aaa(new FabricItemSettings().group(ModItemGroup.MENDELISM)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Mendelism.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Mendelism.LOGGER.info("Registering Mod Items for " + Mendelism.MOD_ID);
    }
}
