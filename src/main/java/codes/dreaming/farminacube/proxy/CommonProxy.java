package codes.dreaming.farminacube.proxy;

import codes.dreaming.farminacube.tile.TileGreenhouse;
//import codes.dreaming.farminacube.tile.TileGreenhouseRender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public abstract class CommonProxy {

    public void preInit() {
        codes.dreaming.farminacube.blocks.StartupCommon.preInitCommon();
    }

    public void init() {
    }

    public void postInit() {
    }
    public void registerRenderers() {
    }

    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    abstract public boolean isDedicatedServer();
}