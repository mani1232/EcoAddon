package mani123.ru.ecoaddon.RecipeMethods;

import com.willfp.eco.core.config.interfaces.Config;
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

    private static final ArrayList<NamespacedKey> StoneCutterNamespace = new ArrayList<>();

    public static void StoneCutterListener(@NotNull final EcoAddon plugin) {
        if (!plugin.getConfigYml().getBool("enableStoneCutter")) return;
        for (Config CfgSub : plugin.getCraftsYml().getSubsections("StoneCutter")) {
            ItemStack input = Items.lookup(CfgSub.getFormattedString("input")).getItem();
            ItemStack result = Items.lookup(CfgSub.getFormattedString("result")).getItem();
            String group = CfgSub.getFormattedString("group");
            NamespacedKey namespacedKey = NamespacedKeyUtils.create("ecoaddon", CfgSub.getFormattedString("id"));
            StoneCutterNamespace.add(namespacedKey);
            StonecuttingRecipe stonecuttingRecipe = new StonecuttingRecipe(namespacedKey, result, (RecipeChoice) input);
            stonecuttingRecipe.setGroup(group);
            Bukkit.addRecipe(stonecuttingRecipe);
        }
    }

    public static void ClearRecipes() {
        for (NamespacedKey namespacedKey : StoneCutterNamespace) {
            Bukkit.removeRecipe(namespacedKey);
        }
        StoneCutterNamespace.clear();
    }

}