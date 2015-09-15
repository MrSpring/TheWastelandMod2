package dk.mrspring.wasteland.common.schematic;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.*;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class Schematic
{
    public int height, length, width;
    byte[] blocks, metadata;
    Map<Integer, Block> blockPalette;
    List<NBTTagCompound> tileEntities;

    public Schematic(NBTTagCompound compound)
    {
        this.loadFromNBT(compound);
    }

    public void loadFromNBT(NBTTagCompound compound)
    {
        this.height = compound.getInteger("Height");
        this.length = compound.getInteger("Length");
        this.width = compound.getInteger("Width");

        this.blocks = compound.getByteArray("Blocks");
        this.metadata = compound.getByteArray("Data");

        this.loadBlockPalette(compound.getCompoundTag("SchematicaMapping"));
        this.loadTileEntities(compound.getTagList("TileEntities", 10));
    }

    public IBlockState getBlockAt(BlockPos localPos)
    {
        if (!isInRange(localPos))
        {
            System.out.println("Pos: " + localPos.toString() + ", is out of range: " + width + ", " + height + ", " + length);
            return Blocks.air.getDefaultState();
        }
        int index = localPos.getX();
        index += localPos.getZ() * length;
        index += localPos.getY() * (width * length);
        byte blockId = blocks[index], meta = metadata[index];
//        System.out.println("Getting block at: " + localPos + ", w: " + width + ", h: " + height + ", l: " + length + ", returning id: " + blockId);
        return convertUsingBlockPalette(blockId, meta);
    }

    public IBlockState convertUsingBlockPalette(int id, int metadata)
    {
        Block fromPalette = blockPalette.get(id);
        if (fromPalette == null)
            System.out.println("Id: "+id+", returned null.");
        else System.out.println("Id: "+id+", did not return null, but: "+fromPalette.getUnlocalizedName()+", metadata: "+metadata);
        if (fromPalette == null) return Blocks.air.getDefaultState();
        else return fromPalette.getStateFromMeta(metadata);
    }

    private boolean isInRange(BlockPos pos)
    {
        return pos.getX() >= 0 && pos.getX() < width &&
                pos.getY() >= 0 && pos.getY() < height &&
                pos.getZ() >= 0 && pos.getZ() < length;
    }

    private void loadTileEntities(NBTTagList list)
    {
        this.tileEntities = new ArrayList<NBTTagCompound>();
        for (int i = 0; i < list.tagCount(); i++)
            tileEntities.add(list.getCompoundTagAt(i));
    }

    private void loadBlockPalette(NBTTagCompound mapping)
    {
        this.blockPalette = new HashMap<Integer, Block>();
        for (Object o : mapping.getKeySet())
            if (o instanceof String)
            {
                String key = (String) o;
                NBTBase tag = mapping.getTag(key);
                System.out.println(key+", "+tag.getClass().getSimpleName());
                if (tag instanceof NBTTagShort)
                {
                    Block block = Block.getBlockFromName(key);
                    int id = ((NBTTagShort) tag).getInt();
                    blockPalette.put(id, block);
                }
            }
    }
}
