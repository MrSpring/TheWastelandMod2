package dk.mrspring.wasteland.common.world;

import dk.mrspring.wasteland.common.world.biome.WorldChunkManagerWasteland;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class WorldTypeWasteland extends WorldType
{
    public WorldTypeWasteland(String name)
    {
        super(name);
    }

    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerWasteland(world);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return super.getChunkGenerator(world, generatorOptions);
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String chunkProviderSettingsJson)
    {
        return super.getBiomeLayer(worldSeed, parentLayer, chunkProviderSettingsJson);
    }
}
