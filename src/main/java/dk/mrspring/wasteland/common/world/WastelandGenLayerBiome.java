package dk.mrspring.wasteland.common.world;

import dk.mrspring.wasteland.common.WastelandBiomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15-09-2015 for TheWastelandMod.
 */
public class WastelandGenLayerBiome extends GenLayer
{
    private List<BiomeManager.BiomeEntry> biomes = new ArrayList<>();

    public WastelandGenLayerBiome(long seed, GenLayer parent)
    {
        super(seed);
        super.parent = parent;

        for (int i = 0; i < 1; ++i)
        {
            this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.city, 10));
        }

        for (int i = 0; i < 3; ++i)
        {
            this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.radioactive, 10));
        }

        for (int i = 0; i < 10; ++i)
        {
            this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.forest, 10));
            this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.mountains, 10));
        }

        for (int i = 0; i < 40; ++i)
        {
            this.biomes.add(new BiomeManager.BiomeEntry(WastelandBiomes.apocalypse, 10));
        }
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType type)
    {
        GenLayerIsland genLayerIsland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genLayerFuzzyZoom = new GenLayerFuzzyZoom(2000L, genLayerIsland);
        GenLayerAddIsland genLayerAddIsland = new GenLayerAddIsland(1L, genLayerFuzzyZoom);
        GenLayerZoom genLayerZoom = new GenLayerZoom(2001L, genLayerAddIsland);
        genLayerAddIsland = new GenLayerAddIsland(2L, genLayerZoom);
        genLayerAddIsland = new GenLayerAddIsland(50L, genLayerAddIsland);
        genLayerAddIsland = new GenLayerAddIsland(70L, genLayerAddIsland);
        GenLayerRemoveTooMuchOcean genLayerDecreaseOcean = new GenLayerRemoveTooMuchOcean(2L, genLayerAddIsland);
        GenLayerAddSnow genLayerAddSnow = new GenLayerAddSnow(2L, genLayerDecreaseOcean);
        genLayerAddIsland = new GenLayerAddIsland(3L, genLayerAddSnow);
        GenLayerEdge genLayerEdge = new GenLayerEdge(2L, genLayerAddIsland, GenLayerEdge.Mode.COOL_WARM);
        genLayerEdge = new GenLayerEdge(2L, genLayerEdge, GenLayerEdge.Mode.HEAT_ICE);
        genLayerEdge = new GenLayerEdge(3L, genLayerEdge, GenLayerEdge.Mode.SPECIAL);
        genLayerZoom = new GenLayerZoom(2002L, genLayerEdge);
        genLayerZoom = new GenLayerZoom(2003L, genLayerZoom);
        genLayerAddIsland = new GenLayerAddIsland(4L, genLayerZoom);
        GenLayerAddMushroomIsland genLayerAddMushroomIsland = new GenLayerAddMushroomIsland(5L, genLayerAddIsland);
        GenLayerDeepOcean genLayerDeepOcean = new GenLayerDeepOcean(4L, genLayerAddMushroomIsland);
        GenLayer genLayer2 = GenLayerZoom.magnify(1000L, genLayerDeepOcean, 0);
        int biomeSize = getModdedBiomeSize(type, 4);
        GenLayer genLayer = GenLayerZoom.magnify(1000L, genLayer2, 0);
        GenLayer object = type.getBiomeLayer(seed, genLayer2, "");
        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genLayer, 2);
        GenLayerHills genlayerhills = new GenLayerHills(1000L, object, genlayer1);
        genLayer = GenLayerZoom.magnify(1000L, genLayer, 2);
        genLayer = GenLayerZoom.magnify(1000L, genLayer, biomeSize);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genLayer);
        Object var24 = new GenLayerRareBiome(1001L, genlayerhills);

        for (int genlayersmooth1 = 0; genlayersmooth1 < biomeSize; ++genlayersmooth1)
        {
            var24 = new GenLayerZoom((long) (1000 + genlayersmooth1), (GenLayer) var24);
            if (genlayersmooth1 == 0)
            {
                var24 = new GenLayerAddIsland(3L, (GenLayer) var24);
            }

            if (genlayersmooth1 == 1)
            {
                var24 = new GenLayerShore(1000L, (GenLayer) var24);
            }
        }

        GenLayerSmooth var25 = new GenLayerSmooth(1000L, (GenLayer) var24);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, var25, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        var25.initWorldGenSeed(seed);
        genlayerrivermix.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        return new GenLayer[]{genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }

    public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        super.parent.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
        int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

        for (int i1 = 0; i1 < p_75904_4_; ++i1)
        {
            for (int j1 = 0; j1 < p_75904_3_; ++j1)
            {
                this.initChunkSeed((long) (j1 + p_75904_1_), (long) (i1 + p_75904_2_));
                aint1[j1 + i1 * p_75904_3_] = ((BiomeManager.BiomeEntry) WeightedRandom.getRandomItem(this.biomes, (int) (this.nextLong((long) (WeightedRandom.getTotalWeight(this.biomes) / 10)) * 10L))).biome.biomeID;
            }
        }

        return aint1;
    }
}
