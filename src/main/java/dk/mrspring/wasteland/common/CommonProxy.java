package dk.mrspring.wasteland.common;

import dk.mrspring.wasteland.TheWastelandMod;
import dk.mrspring.wasteland.common.config.GeneratorConfig;
import dk.mrspring.wasteland.common.config.LootConfig;
import dk.mrspring.wasteland.common.json.GeneratorConfigWrapper;
import dk.mrspring.wasteland.common.json.LootConfigWrapper;
import dk.mrspring.wasteland.common.world.WorldTypeWasteland;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        GeneratorData.getInstance().loadDefaults();
        TheWastelandMod.schematic_placer = new ItemSchematicPlacer();
        GameRegistry.registerItem(TheWastelandMod.schematic_placer, "schematic_placer");
        // TODO: load config wrappers from json, backwards comp.
        // TODO: Add in-game config screen. Hook into forge mod list config button
        GeneratorConfig.getInstance().loadFromWrapper(new GeneratorConfigWrapper());
        LootConfig.getInstance().loadFromWrapper(new LootConfigWrapper());
    }

    public void init(FMLInitializationEvent event)
    {
        TheWastelandMod.instance.wastelandWorldType = new WorldTypeWasteland();
        WastelandBiomes.loadBiomes(GeneratorConfig.getInstance());
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
