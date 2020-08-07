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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.Sys;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class GreenhouseItem extends Item {
    public GreenhouseItem(){
        this.setMaxStackSize(1);
        this.setCreativeTab(MISC);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        NBTTagCompound TagC = null;
        IBlockState seed = worldIn.getBlockState(pos);
        if (player.getHeldItem(hand).getTagCompound() == null){
            player.getHeldItem(hand).setTagCompound(new NBTTagCompound());
            TagC = player.getHeldItem(hand).getTagCompound();
        } else if(TagC == null){
            TagC = player.getHeldItem(hand).getTagCompound();
        }
        if (worldIn.getBlockState(pos.down()).getBlock() == Block.getBlockFromName("minecraft:farmland")) {
            NBTUtil.writeBlockState(TagC, seed);
            TagC.setBoolean("bound", true);
        }else if(worldIn.getBlockState(pos.up()).getBlock() == Block.getBlockFromName("minecraft:air") & NBTUtil.readBlockState(TagC).getBlock() != Block.getBlockFromName("minecraft:air")){
            worldIn.setBlockState(pos.up(), NBTUtil.readBlockState(TagC));
            if(!player.isCreative()){
                player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbtTagCompound;
        if (stack.getTagCompound() !=null){
            nbtTagCompound = stack.getTagCompound();
        }else{
            stack.setTagCompound(new NBTTagCompound());
            nbtTagCompound = stack.getTagCompound();
        }
        if (nbtTagCompound != null & nbtTagCompound.hasKey("bound") & nbtTagCompound.getBoolean("bound") == true){
            tooltip.add("Currently setted to: " + StringUtils.remove(StringUtils.remove(NBTUtil.readBlockState(nbtTagCompound).getBlock().toString(), "Block{"), "}"));
        } else {
            tooltip.add("Right click on crop to set");
        }
    }
}
