package dk.mrspring.wasteland.common.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class GenLayerBiomeTester extends GenLayer
{
    public GenLayerBiomeTester(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] ints = IntCache.getIntCache(areaWidth * areaHeight);
        for (int y = 0; y < areaHeight; y++)
        {
            for (int x = 0; x < areaWidth; x++)
            {
                this.initChunkSeed((long) (areaX + x), (long) (areaY + y));
                ints[x + y * areaWidth] = this.nextInt(10) == 0 ? 2 : 1;
            }
        }
        return ints;
    }
}
