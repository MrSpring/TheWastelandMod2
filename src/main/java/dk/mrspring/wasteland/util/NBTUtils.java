package dk.mrspring.wasteland.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class NBTUtils
{
    public static NBTTagCompound getFromResource(ResourceLocation location)
    {
        try
        {
            IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(location);
            DataInputStream input = new DataInputStream(new GZIPInputStream(resource.getInputStream()));
            return CompressedStreamTools.read(input);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
