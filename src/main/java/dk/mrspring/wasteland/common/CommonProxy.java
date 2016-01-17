package dk.mrspring.wasteland.common;

import dk.mrspring.wasteland.TheWastelandMod;
import dk.mrspring.wasteland.common.config.WorldGenConfig;
import dk.mrspring.wasteland.common.util.FileUtils;
import dk.mrspring.wasteland.common.world.WorldTypeWasteland;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        File config = new File(event.getModConfigurationDirectory(), "TheWastelandMod");
        TheWastelandMod.worldGenConfigFile = new File(config, "world_gen.json");
        TheWastelandMod.worldGenConfig = FileUtils.readJsonFile(TheWastelandMod.worldGenConfigFile, WorldGenConfig.class, true);
        TheWastelandMod.wastelandType = new WorldTypeWasteland("wasteland");

        WastelandBiomes.initialize(TheWastelandMod.worldGenConfig);
    }
}
