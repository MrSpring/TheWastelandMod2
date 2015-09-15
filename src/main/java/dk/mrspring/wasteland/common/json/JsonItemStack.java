package dk.mrspring.wasteland.common.json;

import dk.mrspring.javanbt.NBTJsonDecompile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Map;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class JsonItemStack
{
    String item_name = "";
    int stack_size = 1;
    int metadata = 0;
    Map<String, Object> tag = null;

    public ItemStack toItemStack()
    {
        if (!item_name.isEmpty())
        {
            ItemStack stack = GameRegistry.makeItemStack(item_name, metadata, stack_size, "");
            if (stack != null)
            {
                stack.setItemDamage(metadata);
                if (tag != null)
                {
                    NBTTagCompound compound = NBTJsonDecompile.createFromJsonObject(tag);
                    stack.setTagCompound(compound);
                }
                return stack;
            } else return null;
        } else return null;
    }
}
