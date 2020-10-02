package codes.dreaming.farminacube.proxy;
import codes.dreaming.farminacube.tile.TileGreenhouse;
//import codes.dreaming.farminacube.tile.TileGreenhouseRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientOnlyProxy extends CommonProxy
{
    public void preInit() {
        super.preInit();
        codes.dreaming.farminacube.blocks.StartupClientOnly.preInitClientOnly();
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
    public void registerRenderers() {
        //ClientRegistry.bindTileEntitySpecialRenderer(TileGreenhouse.class, new TileGreenhouseRender());
    }

    @Override
    public boolean isDedicatedServer() {return false;}

}