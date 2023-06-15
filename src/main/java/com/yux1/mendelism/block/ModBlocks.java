package com.yux1.mendelism.block;

import com.yux1.mendelism.Mendelism;
import com.yux1.mendelism.block.custom.ModPeaCropBlock;
import com.yux1.mendelism.block.custom.ModPeaCropPlantBlock;
import com.yux1.mendelism.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBlocks {

    public static final Block PEA_CROP = registerBlockWithoutBlockItem("pea_crop",
            new ModPeaCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    //野生型豌豆植株
    public static final Block PEA_CROP_PLANT = registerBlock("pea_crop_plant",
            new ModPeaCropPlantBlock(FabricBlockSettings.copy(Blocks.DANDELION), ModPeaCropPlantBlock.Genotype.NONE),
            ModItemGroup.MENDELISM);

    private static Block registerBlock(String name, Block block, ItemGroup itemGroup) {
        registerBlockItem(name, block, itemGroup);
        return Registry.register(Registry.BLOCK, new Identifier(Mendelism.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup itemGroup, String tooltipKey) {
        registerBlockItem(name, block, itemGroup, tooltipKey);
        return Registry.register(Registry.BLOCK, new Identifier(Mendelism.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Mendelism.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup itemGroup) {
        return Registry.register(Registry.ITEM, new Identifier(Mendelism.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup itemGroup, String tooltipKey) {
        return Registry.register(Registry.ITEM, new Identifier(Mendelism.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup))
                {
                    @Override
                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                        tooltip.add(new TranslatableText(tooltipKey));
                    }
                });
    }

    public static void registerModBlocks() {
        Mendelism.LOGGER.info("Registering ModBlocks for " + Mendelism.MOD_ID);
    }
}
