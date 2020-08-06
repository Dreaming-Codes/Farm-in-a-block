package codes.dreaming.farminacube.proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ClientOnlyProxy extends CommonProxy
{
    public void preInit() {
        super.preInit();
        codes.dreaming.farminacube.blocks.StartupClientOnly.preInitClientOnly();
        codes.dreaming.farminacube.items.StartupClientOnly.preInitClientOnly();
    }
    public void init() {
        super.init();
    }
    public void postInit() {
        super.postInit();
    }
    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
            return entityPlayerMP.interactionManager.isCreative();
        } else if (player instanceof EntityPlayerSP) {
            return Minecraft.getMinecraft().playerController.isInCreativeMode();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {return false;}

}