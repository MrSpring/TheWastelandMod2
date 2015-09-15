package dk.mrspring.wasteland.common.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
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
        return new ChunkProviderWasteland(world);
    }

    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new ChunkManagerWasteland(world);
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String chunkProviderSettingsJson)
    {
        WastelandGenLayerBiome ret = new WastelandGenLayerBiome(200L, parentLayer);
        GenLayer ret1 = GenLayerZoom.magnify(1000L, ret, 2);
        return new GenLayerBiomeEdge(1000L, ret1);
    }
}
