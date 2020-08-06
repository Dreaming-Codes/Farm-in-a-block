package codes.dreaming.farminacube.items;

import static net.minecraft.creativetab.CreativeTabs.MISC;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GreenhouseItem extends Item {
    public GreenhouseItem(){
        this.setMaxStackSize(1);
        this.setCreativeTab(MISC);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState seed = worldIn.getBlockState(pos);
        if (player.getHeldItem(hand).getTagCompound() == null)
            player.getHeldItem(hand).setTagCompound(new NBTTagCompound());
        if (worldIn.getBlockState(pos.down()).getBlock() == Block.getBlockFromName("minecraft:farmland")) {
            System.out.println(player + "used greenhouse on" + seed);
            NBTUtil.writeBlockState(player.getHeldItem(hand).getTagCompound(), seed);
        }else if(worldIn.getBlockState(pos.up()).getBlock() == Block.getBlockFromName("minecraft:air") & NBTUtil.readBlockState(player.getHeldItem(hand).getTagCompound()).getBlock() != Block.getBlockFromName("minecraft:air")){
            worldIn.setBlockState(pos.up(), NBTUtil.readBlockState(player.getHeldItem(hand).getTagCompound()));
            if(!player.isCreative()){
                player.getHeldItem(hand).setCount(0);
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
