package dk.mrspring.wasteland.client;

import dk.mrspring.wasteland.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class ClientProxy extends CommonProxy
{
    ClientEventHandler eventHandler;

    @Override
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        super.preInit(event);

        eventHandler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }
}
