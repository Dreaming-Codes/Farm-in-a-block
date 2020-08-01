package codes.dreaming.farminacube.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class GreenhouseBlock extends Block {
    private static final AxisAlignedBB BOTTOM = new AxisAlignedBB(0, 0, 0, 1, 0.062, 1);
    private static final AxisAlignedBB UNO_UNO = new AxisAlignedBB(0, 0.062, 0, 1, 0.188, 0.062);
    private static final AxisAlignedBB UNO_DUE = new AxisAlignedBB(0, 0.062, 0.938, 1, 0.188, 1);
    private static final AxisAlignedBB UNO_TRE = new AxisAlignedBB(0, 0.062, 0.062, 0.062, 0.188, 0.938);
    private static final AxisAlignedBB UNO_QUATTRO = new AxisAlignedBB(0.938, 0.062, 0.062, 1, 0.188, 0.938);
    private static final AxisAlignedBB DUE_UNO = new AxisAlignedBB(0.062, 0.188, 0.062, 0.938, 0.312, 0.125);
    private static final AxisAlignedBB DUE_TRE = new AxisAlignedBB(0.062, 0.188, 0.125, 0.125, 0.312, 0.875);
    private static final AxisAlignedBB DUE_DUE = new AxisAlignedBB(0.062, 0.188, 0.875, 0.938, 0.312, 0.938);
    private static final AxisAlignedBB DUE_QUATTRO = new AxisAlignedBB(0.875, 0.188, 0.125, 0.938, 0.312, 0.875);
    private static final AxisAlignedBB TRE_QUATTRO = new AxisAlignedBB(0.812, 0.312, 0.188, 0.875, 0.438, 0.812);
    private static final AxisAlignedBB TRE_DUE = new AxisAlignedBB(0.125, 0.312, 0.812, 0.875, 0.438, 0.875);
    private static final AxisAlignedBB TRE_TRE = new AxisAlignedBB(0.125, 0.312, 0.188, 0.188, 0.438, 0.812);
    private static final AxisAlignedBB TRE_UNO = new AxisAlignedBB(0.125, 0.312, 0.125, 0.875, 0.438, 0.188);
    private static final AxisAlignedBB TOP = new AxisAlignedBB(0.188, 0.438, 0.188, 0.812, 0.5, 0.812);
    private static final AxisAlignedBB LIGHT = new AxisAlignedBB(0.438, 0.375, 0.438, 0.562, 0.438, 0.562);

    private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(BOTTOM, UNO_UNO, UNO_DUE, UNO_TRE, UNO_QUATTRO, DUE_UNO, DUE_TRE, DUE_DUE, DUE_QUATTRO, TRE_QUATTRO, TRE_DUE, TRE_TRE, TRE_UNO, TOP, LIGHT);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);

    public GreenhouseBlock() {
        super(Material.GLASS);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setLightLevel(10);
    }

    @Override
    public boolean isFullCube(IBlockState iBlockState) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState iBlockState){
        return false;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isTranslucent(IBlockState state)
    {
        return true;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState)
    {
        entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
        for (AxisAlignedBB box : COLLISION_BOXES)
        {
            if (entityBox.intersects(box))
                collidingBoxes.add(box.offset(pos));
        }
    }

    @Override
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end)
    {
        double distanceSq;
        double distanceSqShortest = Double.POSITIVE_INFINITY;
        RayTraceResult resultClosest = null;
        RayTraceResult result;
        start = start.subtract(pos.getX(), pos.getY(), pos.getZ());
        end = end.subtract(pos.getX(), pos.getY(), pos.getZ());
        for (AxisAlignedBB box : COLLISION_BOXES)
        {
            result = box.calculateIntercept(start, end);
            if (result == null)
                continue;

            distanceSq = result.hitVec.squareDistanceTo(start);
            if (distanceSq < distanceSqShortest)
            {
                distanceSqShortest = distanceSq;
                resultClosest = result;
            }
        }
        return resultClosest == null ? null : new RayTraceResult(RayTraceResult.Type.BLOCK, resultClosest.hitVec.addVector(pos.getX(), pos.getY(), pos.getZ()), resultClosest.sideHit, pos);
    }

}