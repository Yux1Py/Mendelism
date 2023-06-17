package com.yux1.mendelism.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent PEA = (new FoodComponent.Builder()).snack().hunger(2).saturationModifier(0.1F).build();

    public static final FoodComponent PEA_CAKE = (new FoodComponent.Builder()).snack().hunger(6).saturationModifier(0.6F).build();

    public static final FoodComponent PEA_THICK_SOUP = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.8F).build();
}
