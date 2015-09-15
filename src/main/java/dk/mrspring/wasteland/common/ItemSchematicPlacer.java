package dk.mrspring.wasteland.common;

import dk.mrspring.wasteland.common.schematic.Schematic;
import dk.mrspring.wasteland.util.Generator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created on 14-09-2015 for TheWastelandMod.
 */
public class ItemSchematicPlacer extends Item
{
    public ItemSchematicPlacer()
    {
        this.setUnlocalizedName("schematic_placer");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (worldIn.isRemote) return super.onItemRightClick(itemStackIn, worldIn, playerIn);
        MovingObjectPosition position = getMovingObjectPositionFromPlayer(worldIn, playerIn, false);
        if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            BlockPos pos = position.getBlockPos();
            Generator generator = new Generator(worldIn, pos);
            Schematic s = GeneratorData.getInstance().getSchematic(2);
            generator.placeSchematic(s, new BlockPos(0, 0, 0));
        }
        return super.onItemRightClick(itemStackIn, worldIn, playerIn);
    }
}
