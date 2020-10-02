/*
package codes.dreaming.farminacube.network;

import codes.dreaming.farminacube.tile.TileGreenhouse;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateGrow implements IMessage {
    private BlockPos pos;
    private ItemStack stack;
    private long lastChangeTime;

    public PacketUpdateGrow(BlockPos pos, ItemStack stack, long lastChangeTime) {
        this.pos = pos;
        this.stack = stack;
        this.lastChangeTime = lastChangeTime;
    }

    public PacketUpdateGrow(TileGreenhouse te) {
        this(te.getPos(), te.inventory.getStackInSlot(0), te.lastChangeTime);
    }

    public PacketUpdateGrow() {
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        ByteBufUtils.writeItemStack(buf, stack);
        buf.writeLong(lastChangeTime);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        stack = ByteBufUtils.readItemStack(buf);
        lastChangeTime = buf.readLong();
    }
    public static class Handler implements IMessageHandler<PacketUpdateGrow, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdateGrow message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileGreenhouse te = (TileGreenhouse)Minecraft.getMinecraft().world.getTileEntity(message.pos);
                te.inventory.setStackInSlot(0, message.stack);
                te.lastChangeTime = message.lastChangeTime;
            });
            return null;
        }

    }
}
public class PacketRequestUpdatePedestal implements IMessage {

    private BlockPos pos;
    private int dimension;

    public PacketRequestUpdatePedestal(BlockPos pos, int dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    public PacketRequestUpdatePedestal(TileGreenhouse te) {
        this(te.getPos(), te.getWorld().provider.getDimension());
    }

    public PacketRequestUpdatePedestal() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketRequestUpdatePedestal, PacketUpdateGrow> {

        @Override
        public PacketUpdateGrow onMessage(PacketRequestUpdatePedestal message, MessageContext ctx) {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
            TileGreenhouse te = (TileGreenhouse) world.getTileEntity(message.pos);
            if (te != null) {
                return new PacketUpdateGrow(te);
            } else {
                return null;
            }
        }

    }

}

 */