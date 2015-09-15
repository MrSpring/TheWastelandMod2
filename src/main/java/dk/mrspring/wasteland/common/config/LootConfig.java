package dk.mrspring.wasteland.common.config;

import dk.mrspring.wasteland.common.json.JsonItemStack;
import dk.mrspring.wasteland.common.json.LootConfigWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ChestGenHooks;

import java.util.*;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class LootConfig
{
    private static LootConfig instance = new LootConfig();

    public static LootConfig getInstance()
    {
        return instance;
    }

    //    List<LootCategory> lootCategories = new ArrayList<>();
    Map<String, LootCategory> lootCategories = new HashMap<>();

    public void loadFromWrapper(LootConfigWrapper wrapper)
    {
        for (Map.Entry<String, LootConfigWrapper.LootCategory> entry : wrapper.loot_categories.entrySet())
            this.addLootCategory(new LootCategory(entry.getValue(), entry.getKey()));
    }

    public void addLootCategory(LootCategory category)
    {
        if (!lootCategories.containsKey(category.id))
            lootCategories.put(category.id, category);
    }

    public LootCategory[] getCategories(String... names)
    {
        List<LootCategory> found = new ArrayList<>();
        for (String name : names)
            if (lootCategories.containsKey(name))
                found.add(lootCategories.get(name));
        return found.toArray(new LootCategory[found.size()]);
    }

    private ItemStack[] compileFromJson(JsonItemStack[] jsonStacks)
    {
        List<ItemStack> stacks = new ArrayList<>();
        for (JsonItemStack jsonStack : jsonStacks)
        {
            ItemStack fromJson = jsonStack.toItemStack();
            if (fromJson != null) stacks.add(fromJson);
        }
        return stacks.toArray(new ItemStack[stacks.size()]);
    }

    private class LootCategory
    {
        String id;
        int rarity, vanillaChance;
        ItemStack[] loot, vanillaBlacklist;
        boolean useVanilla;

        public LootCategory(LootConfigWrapper.LootCategory wrapper, String id)
        {
            this.id = id;
            this.useVanilla = wrapper.use_vanilla_loot_list;
            this.rarity = wrapper.rarity;
            this.loot = compileFromJson(wrapper.loot_stacks);
            this.vanillaBlacklist = compileFromJson(wrapper.blacklisted_vanilla_loot);
        }

        public int getRarity()
        {
            return rarity;
        }

        public String getId()
        {
            return id;
        }

        public ItemStack[] getLoot()
        {
            return loot;
        }

        public ItemStack getRandomLootStack(Random rand)
        {
            if (useVanilla && rand.nextInt(100) > vanillaChance)
            {
                return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, rand);
            }
            List<ItemStack> stacks = new ArrayList<>();
            Collections.addAll(stacks, loot);
            return stacks.get(rand.nextInt(stacks.size()));
        }
    }
}
