package dk.mrspring.wasteland.common.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created on 15-09-2015 for TheWastelandMod.
 */
public class BiomeGenWasteland extends BiomeGenBase
{
    public BiomeGenWasteland(int id, String name, Height biomeHeight)
    {
        super(id);

        this.enableRain=true;
        this.setBiomeName(name);
        this.setHeight(biomeHeight);

        super.theBiomeDecorator.deadBushPerChunk = 5;
        super.theBiomeDecorator.flowersPerChunk = -999;
        super.theBiomeDecorator.generateLakes = false;
        super.theBiomeDecorator.grassPerChunk = -999;
        super.theBiomeDecorator.treesPerChunk = -999;
//        this.setTopBlock(ModConfig.getSurfaceBlock(), ModConfig.getSurfaceBlockMeta());
//        this.setFillerBlock(Blocks.stone);
        this.topBlock = Blocks.dirt.getDefaultState();
        this.fillerBlock = Blocks.stone.getDefaultState();
    }
}
