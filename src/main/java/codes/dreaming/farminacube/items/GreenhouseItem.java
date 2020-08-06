package codes.dreaming.farminacube.items;

import static net.minecraft.creativetab.CreativeTabs.MISC;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class GreenhouseItem extends Item {
    public List<String> tooltipe;
    public GreenhouseItem(){
        this.setMaxStackSize(1);
        this.setCreativeTab(MISC);
        tooltipe.add("Right click on a crop placed on a farmland to set it");
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState seed = worldIn.getBlockState(pos);
        if (player.getHeldItem(hand).getTagCompound() == null)
            player.getHeldItem(hand).setTagCompound(new NBTTagCompound());
        if (worldIn.getBlockState(pos.down()).getBlock() == Block.getBlockFromName("minecraft:farmland")) {
            NBTUtil.writeBlockState(player.getHeldItem(hand).getTagCompound(), seed);
            //tooltipe.add("Now set to:" + NBTUtil.readBlockState(player.getHeldItem(hand).getTagCompound()).getBlock());
        }else if(worldIn.getBlockState(pos.up()).getBlock() == Block.getBlockFromName("minecraft:air") & NBTUtil.readBlockState(player.getHeldItem(hand).getTagCompound()).getBlock() != Block.getBlockFromName("minecraft:air")){
            worldIn.setBlockState(pos.up(), NBTUtil.readBlockState(player.getHeldItem(hand).getTagCompound()));
            if(!player.isCreative()){
                player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

    }
}
