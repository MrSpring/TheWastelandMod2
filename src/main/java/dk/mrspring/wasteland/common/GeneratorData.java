package dk.mrspring.wasteland.common;

import dk.mrspring.wasteland.common.schematic.Schematic;
import dk.mrspring.wasteland.util.NBTUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class GeneratorData
{
    public static final String[] SCHEMATICS = new String[]{ // TODO: List<String>
            "LTA_bottom", "LTA_middle", "LTA_top",
            "LTB_bottom", "LTB_middle", "LTB_topA", "LTB_topB", "LTB_topC",
            "LTC_bottom", "LTC_middle", "LTC_top",
            "LTD_bottom", "LTD_middle", "LTD_top",
            "LTE_bottom", "LTE_middle", "LTE_top",
            "LTF_bottom", "LTF_middle", "LTF_top",
            "MAA_bottom", "MAA_middle", "MAA_topA", "MAA_topB",
            "MAB_bottom", "MAB_middle", "MAB_top",
            "MAC_bottom", "MAC_middle", "MAC_top",
            "MAD_bottom", "MAD_middle", "MAD_top",
            "MAE_bottom", "MAE_middle", "MAE_top",
            "MCA",
            "SSA",
            "SSB",
            "STA_bottom", "STA_middle", "STA_top",
            "STB_bottom", "STB_middle", "STB_top",
            "STC_bottom", "STC_middle", "STC_top",
    };

    private static GeneratorData instance = new GeneratorData();

    List<Schematic> loadedSchematics = new ArrayList<Schematic>();

    public static GeneratorData getInstance()
    {
        return instance;
    }

    public void loadDefaults()
    {
        this.load(SCHEMATICS);
    }

    public void load(String... schematics)
    {
        for (String s : schematics)
        {
            Schematic schematic = loadSchematic(s);
            if (schematic != null) loadedSchematics.add(schematic);
        }
    }

    public ResourceLocation makeSchematicLocation(String name)
    {
        return new ResourceLocation("wlm", "schematics/" + name + ".schematic");
    }

    public Schematic loadSchematic(String name)
    {
        NBTTagCompound schematicTag = NBTUtils.getFromResource(makeSchematicLocation(name));
        return schematicTag != null ? new Schematic(schematicTag) : null;
    }

    public Schematic getSchematic(int index)
    {
        return loadedSchematics.get(index);
    }

    public void unload()
    {
        loadedSchematics.clear();
    }

    public List<Schematic> getSchematics()
    {
        return loadedSchematics;
    }
}
