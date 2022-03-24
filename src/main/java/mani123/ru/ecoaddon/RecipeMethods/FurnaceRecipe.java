package mani123.ru.ecoaddon.RecipeMethods;

import com.willfp.eco.core.EcoPlugin;
import com.willfp.eco.core.PluginDependent;
import com.willfp.eco.core.items.Items;
import mani123.ru.ecoaddon.EcoAddon;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

public class FurnaceRecipe extends PluginDependent<EcoPlugin> implements Listener {

    private static int craftCounter = 0;

    public FurnaceRecipe(@NotNull final EcoAddon plugin) {
        super(plugin);
        FurnaceRecipeListener(plugin);
    }

    public void FurnaceRecipeListener(@NotNull final EcoAddon config) {
        for (int i = 0; i < config.getCraftsYml().getSubsections("FurnaceRecipe").size(); i++) {
            ItemStack input = Items.lookup(config.getCraftsYml().getSubsections("FurnaceRecipe").get(i).getFormattedString( "input")).getItem();
            ItemStack result = Items.lookup(config.getCraftsYml().getSubsections("FurnaceRecipe").get(i).getFormattedString("result")).getItem();
            double experience = config.getCraftsYml().getSubsections("FurnaceRecipe").get(i).getDouble("experience");
            int cookingTime = config.getCraftsYml().getSubsections("FurnaceRecipe").get(i).getInt("cookingTime");
            FurnaceRecipeMethods(NamespacedKey.minecraft(config.getCraftsYml().getSubsections("FurnaceRecipe").get(i).getString("id")), result, input, (float) experience, cookingTime);
        }

        //String id = config.getCraftsYml().getString("id").toLowerCase().trim();
    }

    public void FurnaceRecipeMethods(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull ItemStack input, float experience, int cookingTime) {
        RecipeChoice inputChoice = new RecipeChoice.ExactChoice(input);
        Bukkit.addRecipe(new org.bukkit.inventory.FurnaceRecipe(key, result, inputChoice, experience, cookingTime * 20));
        craftCounter++;
    }

    public static int getCraftsCount() {
        return craftCounter;
    }

}
