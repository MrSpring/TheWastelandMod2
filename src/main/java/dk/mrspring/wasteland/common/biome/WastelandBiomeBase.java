package dk.mrspring.wasteland.common.biome;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class WastelandBiomeBase extends BiomeGenBase
{
    public WastelandBiomeBase(int id, String name)
    {
        super(id);

        this.waterColorMultiplier = 0x00FF55;

        this.spawnableCreatureList.clear();

        this.setBiomeName(name);
    }

    public WastelandBiomeBase setTopBlock(IBlockState block)
    {
        this.topBlock = block;
        return this;
    }

    public WastelandBiomeBase setFillerBlock(IBlockState block)
    {
        this.fillerBlock = block;
        return this;
    }
}
