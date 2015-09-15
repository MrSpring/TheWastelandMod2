package dk.mrspring.wasteland.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.*;
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
            return getFromStream(resource.getInputStream());
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static NBTTagCompound getFromFile(File file)
    {
        try
        {
            return getFromStream(new FileInputStream(file));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static NBTTagCompound getFromStream(InputStream stream)
    {
        try
        {
            DataInputStream input = new DataInputStream(new GZIPInputStream(stream));
            return CompressedStreamTools.read(input);
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
