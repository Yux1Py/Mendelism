package com.yux1.mendelism.util;

import com.yux1.mendelism.Mendelism;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {
    public static class Items{

        public static final TagKey<Item> GREEN_PEA = createTag("green_pea");
        public static final TagKey<Item> YELLOW_PEA = createTag("yellow_pea");

        public static final TagKey<Item> EMASCULATION = createCommonTag("emasculation");
        public static final TagKey<Item> BRUSH = createCommonTag("brush");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(Registry.ITEM_KEY, new Identifier(Mendelism.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name){
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }

    }
}
