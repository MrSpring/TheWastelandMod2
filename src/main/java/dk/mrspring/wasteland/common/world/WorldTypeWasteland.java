package dk.mrspring.wasteland.common.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

/**
 * Created on 15-09-2015 for TheWastelandMod.
 */
public class WorldTypeWasteland extends WorldType // TODO: Customization
{
    public WorldTypeWasteland()
    {
        super("wasteland");
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderWasteland(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), "");
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String chunkProviderSettingsJson)
    {
        WastelandGenLayerBiome ret = new WastelandGenLayerBiome(200L, parentLayer, this);
        GenLayer ret1 = GenLayerZoom.magnify(1000L, ret, 2);
        GenLayerBiomeEdge ret2 = new GenLayerBiomeEdge(1000L, ret1);
        return ret2;
    }
}
