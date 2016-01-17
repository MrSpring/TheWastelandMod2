package dk.mrspring.wasteland.common.world.gen.layer;

import dk.mrspring.wasteland.common.WastelandBiomes;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.Arrays;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class GenLayerTester extends GenLayer
{
    public GenLayerTester(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] ints = IntCache.getIntCache(areaWidth * areaHeight);
        Arrays.fill(ints, WastelandBiomes.apocalypse.biomeID);
        /*for (int y = 0; y < areaHeight; y++)
        {
            for (int x = 0; x < areaWidth; x++)
            {
                this.initChunkSeed((long) (areaX + x), (long) (areaY + y));
                ints[x + y * areaWidth] = this.nextInt(10) == 0 ? 1 : 0;
            }
        }*/
        return ints;
    }
}
