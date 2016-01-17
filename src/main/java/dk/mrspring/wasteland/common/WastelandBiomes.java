package dk.mrspring.wasteland.common;

import dk.mrspring.wasteland.common.biome.WastelandBiomeBase;
import dk.mrspring.wasteland.common.config.WorldGenConfig;
import dk.mrspring.wasteland.common.world.biome.WorldChunkManagerWasteland;
import net.minecraft.init.Blocks;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class WastelandBiomes
{
    public static WastelandBiomeBase apocalypse;

    public static void initialize(WorldGenConfig config)
    {
        apocalypse = new WastelandBiomeBase(config.wasteland_biome_id, "wasteland").setTopBlock(Blocks.dirt.getDefaultState()).setFillerBlock(Blocks.stone.getDefaultState());

        WorldChunkManagerWasteland.allowedBiomes.add(apocalypse);
    }
}
