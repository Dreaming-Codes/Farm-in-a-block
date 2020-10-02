package codes.dreaming.farminacube.blocks;

import codes.dreaming.farminacube.tile.TileGreenhouse;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class GreenhouseBlock extends BlockContainer implements IGrowable {
    private static final AxisAlignedBB FLOOR = new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.125, 0.938);
    private static final AxisAlignedBB WALL4 = new AxisAlignedBB(0, 0.062, 0, 0.062, 0.188, 0.938);
    private static final AxisAlignedBB WALL3 = new AxisAlignedBB(0.062, 0.062, 0, 1, 0.188, 0.062);
    private static final AxisAlignedBB WALL2 = new AxisAlignedBB(0, 0.062, 0.938, 0.938, 0.188, 1);
    private static final AxisAlignedBB WALL1 = new AxisAlignedBB(0.938, 0.062, 0.062, 1, 0.188, 1);

    private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(FLOOR, WALL4, WALL3, WALL2, WALL1);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.188, 1);

    public GreenhouseBlock(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
        return EnumBlockRenderType.MODEL;
    }

    public GreenhouseBlock() {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabs.MISC);
    }


    @Override
    public boolean isFullBlock(IBlockState state){
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState) {
        entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
        for (AxisAlignedBB box : COLLISION_BOXES) {
            if (entityBox.intersects(box))
                collidingBoxes.add(box.offset(pos));
        }
    }

    @Override
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end) {
        double distanceSq;
        double distanceSqShortest = Double.POSITIVE_INFINITY;
        RayTraceResult resultClosest = null;
        RayTraceResult result;
        start = start.subtract(pos.getX(), pos.getY(), pos.getZ());
        end = end.subtract(pos.getX(), pos.getY(), pos.getZ());
        for (AxisAlignedBB box : COLLISION_BOXES) {
            result = box.calculateIntercept(start, end);
            if (result == null)
                continue;

            distanceSq = result.hitVec.squareDistanceTo(start);
            if (distanceSq < distanceSqShortest) {
                distanceSqShortest = distanceSq;
                resultClosest = result;
            }
        }
        return resultClosest == null ? null : new RayTraceResult(RayTraceResult.Type.BLOCK, resultClosest.hitVec.addVector(pos.getX(), pos.getY(), pos.getZ()), resultClosest.sideHit, pos);
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileGreenhouse();
    }
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }
    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack HandItem = playerIn.getHeldItem(hand);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        ItemStackHandler inventory = (ItemStackHandler)tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        inventory.insertItem(0, HandItem, false);
        if(HandItem.getItem() instanceof IPlantable){
            if (!playerIn.isCreative()) {
                HandItem.setCount(-1);
            }
            return true;
        } else {
            System.out.println(inventory.getStackInSlot(0));
            return false;
        }
    }
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }
}