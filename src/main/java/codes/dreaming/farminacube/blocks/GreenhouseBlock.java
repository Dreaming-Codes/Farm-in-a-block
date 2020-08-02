package codes.dreaming.farminacube.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GreenhouseBlock extends Block {
    private static final AxisAlignedBB FLOOR = new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.125, 0.938);
    private static final AxisAlignedBB WALL4 = new AxisAlignedBB(0, 0.062, 0, 0.062, 0.188, 0.938);
    private static final AxisAlignedBB WALL3 = new AxisAlignedBB(0.062, 0.062, 0, 1, 0.188, 0.062);
    private static final AxisAlignedBB WALL2 = new AxisAlignedBB(0, 0.062, 0.938, 0.938, 0.188, 1);
    private static final AxisAlignedBB WALL1 = new AxisAlignedBB(0.938, 0.062, 0.062, 1, 0.188, 1);

    private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(FLOOR, WALL4, WALL3, WALL2, WALL1);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.188, 1);

    public GreenhouseBlock() {
        super(Material.GLASS);
        this.setCreativeTab(CreativeTabs.MISC);
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

}