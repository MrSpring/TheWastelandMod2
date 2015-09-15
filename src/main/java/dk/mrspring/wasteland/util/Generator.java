package dk.mrspring.wasteland.util;

import dk.mrspring.wasteland.common.schematic.Schematic;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class Generator
{
    BlockPos offset;
    public int setBlockFlag = 2;
    World world;

    public Generator(World world, BlockPos offset)
    {
        this.world = world;
        this.offset = offset;
    }

    public Generator placeSchematic(Schematic schematic, BlockPos localPos)
    {
        for (int y = 0; y < schematic.height; y++)
            for (int x = 0; x < schematic.width; x++)
                for (int z = 0; z < schematic.length; z++)
                {
                    BlockPos placePos = new BlockPos(x, y, z).add(localPos);
                    System.out.println("Placing at: "+placePos+", world: "+offset(placePos).toString());
                    this.placeBlock(schematic.getBlockAt(placePos), placePos); // TODO: TileEntity read if block instance of ITileEntityProvider
                }
        return this;
    }

    public Generator placeBlock(IBlockState state, BlockPos localPos)
    {
        BlockPos placePos = this.offset(localPos);
        world.setBlockState(placePos, state, setBlockFlag);
        return this;
    }

    public Generator placeBlock(Block block, int metadata, BlockPos localPos)
    {
        return this.placeBlock(block.getStateFromMeta(metadata), localPos);
    }

    public Generator placeBlock(Block block, BlockPos localPos)
    {
        return this.placeBlock(block, 0, localPos);
    }

    public Generator move(BlockPos amount)
    {
        this.offset.add(amount);
        return this;
    }

    public Generator setOffset(BlockPos offset)
    {
        this.offset = offset;
        return this;
    }

    public BlockPos offset(BlockPos local)
    {
        return new BlockPos(local).add(offset);
    }
}
