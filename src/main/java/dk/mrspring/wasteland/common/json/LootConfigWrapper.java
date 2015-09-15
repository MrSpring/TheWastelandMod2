package dk.mrspring.wasteland.common.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class LootConfigWrapper
{
    public Map<String, LootCategory> loot_categories = new HashMap<>();

    public static class LootCategory
    {
        public JsonItemStack[] loot_stacks = null;
        public JsonItemStack[] blacklisted_vanilla_loot = null;
        public boolean use_vanilla_loot_list = false;
        public int rarity = 50;
        public int vanilla_chance = 50;
    }
}
