package dk.mrspring.wasteland.common.world.biome;

import com.google.common.collect.Lists;
import dk.mrspring.wasteland.common.world.gen.layer.GenLayerBiomeTester;
import dk.mrspring.wasteland.common.world.gen.layer.GenLayerTester;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

import java.util.List;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class WorldChunkManagerWasteland extends WorldChunkManager
{
    public static List<BiomeGenBase> allowedBiomes = Lists.newArrayList();

    public WorldChunkManagerWasteland(World world)
    {
        super(world);

        this.getBiomesToSpawnIn().clear();
        this.getBiomesToSpawnIn().addAll(allowedBiomes);
    }

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
        return new GenLayer[]{
                new GenLayerTester(20L), // Generated terrain biome style
                new GenLayerTester(1L),  // Actual biome. Does not really affect generation.
        };
    }
}
