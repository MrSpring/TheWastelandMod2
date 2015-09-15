package dk.mrspring.wasteland.common;

import com.google.common.collect.Lists;
import dk.mrspring.wasteland.common.config.GeneratorConfig;
import dk.mrspring.wasteland.common.world.biome.BiomeGenWasteland;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

import java.util.List;

/**
 * Created on 15-09-2015 for TheWastelandMod.
 */
public class WastelandBiomes
{
    public static final String APOCALYPSE = "Wasteland";
    public static final String MOUNTAINS = "Wasteland Mountains";
    public static final String FOREST = "Wasteland Forest";
    public static final String CITY = "Wasteland City";
    public static final String RADIOACTIVE = "Radioactive Wasteland";

    public static final BiomeGenBase.Height HEIGHT_WASTELAND = new BiomeGenBase.Height(0.1F, 0.05F);
    public static final BiomeGenBase.Height HEIGHT_WASTELAND_CITY = new BiomeGenBase.Height(0.09F, 0.01F);
    public static final BiomeGenBase.Height HEIGHT_WASTELAND_MOUNTAINS = new BiomeGenBase.Height(1F, 0.5F);

    public static BiomeGenBase apocalypse, mountains, forest, city, radioactive;

    public static List<BiomeGenBase> getAllBiomes()
    {
        return Lists.newArrayList(apocalypse, mountains, forest, city, radioactive);
    }

    public static void loadBiomes(GeneratorConfig config)
    {
        apocalypse = new BiomeGenWasteland(config.getBiomeId(APOCALYPSE), APOCALYPSE, HEIGHT_WASTELAND);
        mountains = new BiomeGenWasteland(config.getBiomeId(MOUNTAINS), MOUNTAINS, HEIGHT_WASTELAND_MOUNTAINS);
        forest = new BiomeGenWasteland(config.getBiomeId(FOREST), FOREST, HEIGHT_WASTELAND);
        city = new BiomeGenWasteland(config.getBiomeId(CITY), CITY, HEIGHT_WASTELAND_CITY);
        radioactive = new BiomeGenWasteland(config.getBiomeId(RADIOACTIVE), RADIOACTIVE, HEIGHT_WASTELAND);

        BiomeDictionary.registerBiomeType(apocalypse, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(mountains, Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(forest, Type.WASTELAND, Type.FOREST);
        BiomeDictionary.registerBiomeType(city, Type.WASTELAND, Type.DEAD);
        BiomeDictionary.registerBiomeType(radioactive, Type.WASTELAND);

        BiomeManager.addSpawnBiome(apocalypse);
        BiomeManager.addSpawnBiome(mountains);
        BiomeManager.addSpawnBiome(forest);
        BiomeManager.addSpawnBiome(city);
    }
}
