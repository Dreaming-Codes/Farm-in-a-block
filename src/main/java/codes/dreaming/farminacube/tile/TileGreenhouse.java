package codes.dreaming.farminacube.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileGreenhouse extends TileEntity implements ICapabilityProvider {
    private ItemStackHandler inventory = new ItemStackHandler(1) {

        @Override
        public int getSlotLimit(int slot)
        {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {

            markDirty();

        }

    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {

        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);

    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {

        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);

    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {

        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);

    }

    @Override
    public NBTTagCompound getUpdateTag() {

        return this.writeToNBT(new NBTTagCompound());

    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {

        return new SPacketUpdateTileEntity(getPos(), 0, writeToNBT(new NBTTagCompound()));

    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {

        setPos(pkt.getPos());
        readFromNBT(pkt.getNbtCompound());

    }
    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared()
    {
        final int MAXIMUM_DISTANCE_IN_BLOCKS = Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
        return MAXIMUM_DISTANCE_IN_BLOCKS * MAXIMUM_DISTANCE_IN_BLOCKS;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB infiniteExample = INFINITE_EXTENT_AABB;

        // our gem will stay above the block, up to 1 block higher, so our bounding box is from [x,y,z] to  [x+1, y+2, z+1]
        AxisAlignedBB aabb = new AxisAlignedBB(getPos(), getPos().add(1, 1, 1));
        return aabb;
    }
    private final long INVALID_TIME = 0;
    private long lastTime = INVALID_TIME;
    private double lastAngularPosition;

    public void collideEntityItem(EntityItem item) {

        if(checkNextFreeSlot() != 100) {

            ItemStack copy = item.getItem().copy();
            int count = copy.getCount();

            if(count <= 1) {

                item.setDead();
                copy.setCount(1);

            } else {

                item.getItem().setCount(item.getItem().getCount() - 1);
                copy.setCount(1);

            }

            inventory.insertItem(checkNextFreeSlot(), copy, false);

        }

    }

    public int checkNextFreeSlot() {

        for(int i=0; i < inventory.getSlots(); i++){

            if(inventory.getStackInSlot(i).isEmpty()) {

                return i;

            }

        }

        return 100;

    }

}