package dk.mrspring.wasteland;

import dk.mrspring.wasteland.common.CommonProxy;
import dk.mrspring.wasteland.common.EventHandler;
import dk.mrspring.wasteland.common.GeneratorData;
import dk.mrspring.wasteland.common.ItemSchematicPlacer;
import dk.mrspring.wasteland.common.world.WorldTypeWasteland;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class TheWastelandMod
{
    @SidedProxy(serverSide = "dk.mrspring.wasteland.common.CommonProxy", clientSide = "dk.mrspring.wasteland.client.ClientProxy")
    public static CommonProxy proxy;

    public static Item schematic_placer;

    @Mod.Instance(ModInfo.MOD_ID)
    public static TheWastelandMod instance;

    public WorldType wastelandWorldType;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        GeneratorData.getInstance().loadDefaults();
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        schematic_placer = new ItemSchematicPlacer();
        GameRegistry.registerItem(schematic_placer, "schematic_placer");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
        instance.wastelandWorldType = new WorldTypeWasteland();
    }
}
