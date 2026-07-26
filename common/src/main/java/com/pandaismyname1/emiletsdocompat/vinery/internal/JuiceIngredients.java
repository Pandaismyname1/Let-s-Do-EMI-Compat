package com.pandaismyname1.emiletsdocompat.vinery.internal;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.satisfy.vinery.core.util.JuiceUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Resolves the juice type string stored on a fermentation barrel recipe back into the items that
 * can produce it. Vinery consumes juice like a fuel (the item fills a tank, the recipe only names
 * the resulting fluid), so the juice never shows up as a recipe ingredient on its own.
 * <p>
 * The mapping mirrors {@link JuiceUtil#getJuiceType} and is derived from the same maps, so juices
 * contributed by other mods resolve too.
 */
public final class JuiceIngredients {
    private JuiceIngredients() {
    }

    /**
     * @return the juices matching {@code juiceType}, or {@link EmiStack#EMPTY} if nothing matches.
     */
    public static EmiIngredient of(String juiceType, long amount) {
        if (juiceType == null || juiceType.isEmpty()) {
            return EmiStack.EMPTY;
        }

        var tag = findTag(JuiceUtil.RED_JUICE_TAGS, "red_", juiceType);
        if (tag == null) {
            tag = findTag(JuiceUtil.WHITE_JUICE_TAGS, "white_", juiceType);
        }
        if (tag != null) {
            var ingredient = EmiIngredient.of(tag, amount);
            // EMI only resolves tags it indexed itself; fall back to the vanilla tag contents.
            return ingredient.isEmpty() ? EmiIngredient.of(Ingredient.of(tag), amount) : ingredient;
        }

        var items = new ArrayList<EmiIngredient>();
        for (var entry : JuiceUtil.APPLE_JUICES.entrySet()) {
            if (juiceType.equals(entry.getValue())) {
                items.add(EmiStack.of(entry.getKey()));
            }
        }
        return items.isEmpty() ? EmiStack.EMPTY : EmiIngredient.of(items, amount);
    }

    private static TagKey<Item> findTag(Map<TagKey<Item>, String> tags, String prefix, String juiceType) {
        for (var entry : tags.entrySet()) {
            if (juiceType.equals(prefix + entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * @return how many juice items have to be inserted to reach {@code juiceAmount} tank units.
     */
    public static int bottlesFor(int juiceAmount, int unitsPerItem) {
        if (juiceAmount <= 0 || unitsPerItem <= 0) {
            return 1;
        }
        return Math.max(1, (juiceAmount + unitsPerItem - 1) / unitsPerItem);
    }
}
