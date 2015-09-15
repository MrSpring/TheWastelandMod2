package dk.mrspring.wasteland.common.config;

import dk.mrspring.wasteland.common.json.GeneratorConfigWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 15-09-2015 for TheWastelandMod.
 */
public class GeneratorConfig
{
    private static GeneratorConfig instance = new GeneratorConfig();

    public static GeneratorConfig getInstance()
    {
        return instance;
    }

    public Map<String, Integer> ids;

    public void loadFromWrapper(GeneratorConfigWrapper wrapper)
    {
        this.ids = new HashMap<>(wrapper.biome_ids);
    }

    public int getBiomeId(String biome)
    {
        if (ids == null) throw new IllegalStateException("Generator config not yet loaded!");
        return ids.get(biome);
    }
}
