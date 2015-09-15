package dk.mrspring.wasteland.common.json;

import dk.mrspring.wasteland.common.WastelandBiomes;
import dk.mrspring.wasteland.common.config.GeneratorConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 15-09-2015 for TheWastelandMod.
 */
public class GeneratorConfigWrapper
{
    public static final Map<String, Integer> defaults = new HashMap<>();

    static
    {
        defaults.put(WastelandBiomes.APOCALYPSE, 43);
        defaults.put(WastelandBiomes.MOUNTAINS, 44);
        defaults.put(WastelandBiomes.FOREST, 45);
        defaults.put(WastelandBiomes.CITY, 46);
        defaults.put(WastelandBiomes.RADIOACTIVE, 47);
    }

    public Map<String, Integer> biome_ids = new HashMap<>(defaults);
}
