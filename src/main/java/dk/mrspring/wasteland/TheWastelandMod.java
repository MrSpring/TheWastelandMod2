package dk.mrspring.wasteland;

import dk.mrspring.wasteland.common.CommonProxy;
import dk.mrspring.wasteland.common.config.WorldGenConfig;
import dk.mrspring.wasteland.common.util.FileUtils;
import dk.mrspring.wasteland.common.world.WorldTypeWasteland;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created on 15-01-2016 for TheWastelandMod2.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class TheWastelandMod
{
    public static WorldType wastelandType;

    @Mod.Instance(ModInfo.MOD_ID)
    public static TheWastelandMod instance;

    public static File worldGenConfigFile;

    public static WorldGenConfig worldGenConfig;

    @SidedProxy(serverSide = "dk.mrspring.wasteland.common.CommonProxy", clientSide = "dk.mrspring.wasteland.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        proxy.preInit(event);
    }
}
