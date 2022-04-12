package mani123.ru.ecoaddon.RecipeMethods;

import com.willfp.eco.core.items.Items;
import com.willfp.eco.util.NamespacedKeyUtils;
import mani123.ru.ecoaddon.EcoAddon;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.StonecuttingRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StoneCutter {

    private static ArrayList<NamespacedKey> StoneCutterNamespace = new ArrayList<>();

    public static void StoneCutterListener(@NotNull final EcoAddon plugin) {
        if (plugin.getConfigYml().getBool("enableStoneCutter")) {
            for (int i = 0; i < plugin.getCraftsYml().getSubsections("StoneCutter").size(); i++) {
                if (!Items.lookup(plugin.getCraftsYml().getSubsections("StoneCutter").get(i).getFormattedString("input")).matches(DefaultMethods.getAIR())
                        && !Items.lookup(plugin.getCraftsYml().getSubsections("StoneCutter").get(i).getFormattedString("result")).matches(DefaultMethods.getAIR())) {
                    ItemStack input = Items.lookup(plugin.getCraftsYml().getSubsections("StoneCutter").get(i).getFormattedString("input")).getItem();
                    ItemStack result = Items.lookup(plugin.getCraftsYml().getSubsections("StoneCutter").get(i).getFormattedString("result")).getItem();
                    String group = plugin.getCraftsYml().getSubsections("StoneCutter").get(i).getFormattedString("group");
                    NamespacedKey namespacedKey = NamespacedKeyUtils.create("ecoaddon", plugin.getCraftsYml().getSubsections("StoneCutter").get(i).getString("id").toLowerCase().trim());
                    StoneCutterMethod(namespacedKey, input, result, group);
                }
            }
        }
    }

    public static void StoneCutterMethod(@NotNull NamespacedKey key, @NotNull ItemStack input, @NotNull ItemStack result, String group) {
        RecipeChoice inputChange = new RecipeChoice.ExactChoice(input);
        StonecuttingRecipe recipe = new org.bukkit.inventory.StonecuttingRecipe(key, result, inputChange);
        recipe.setGroup(group);
        Bukkit.addRecipe(recipe);
        StoneCutterNamespace.add(key);
    }

    public static CharSequence getCraftsCount() {
        return String.valueOf(StoneCutterNamespace.size());
    }

    public static ArrayList<NamespacedKey> getCraftsNames() {
        return StoneCutterNamespace;
    }

    public static void ClearRecipes() {
        for (NamespacedKey namespacedKey : StoneCutterNamespace) {
            Bukkit.removeRecipe(namespacedKey);
        }
        StoneCutterNamespace.clear();
    }

}

